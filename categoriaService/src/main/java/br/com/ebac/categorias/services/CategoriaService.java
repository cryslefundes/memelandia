package br.com.ebac.categorias.services;

import br.com.ebac.categorias.adapter.InputCategoria;
import br.com.ebac.categorias.adapter.OutputCategoria;
import br.com.ebac.categorias.adapter.UpdateDescricaoCategoria;
import br.com.ebac.categorias.adapter.UpdateNomeCategoria;
import br.com.ebac.categorias.client.UsuarioClientFeign;
import br.com.ebac.categorias.entities.Categoria;
import br.com.ebac.categorias.repositories.CategoriaRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoriaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriaService.class);
    private final UsuarioClientFeign usuarioClient;
    private final CategoriaRepositorio repositorio;

    public CategoriaService(CategoriaRepositorio repositorio, UsuarioClientFeign usuarioClient) {
        this.repositorio = repositorio;
        this.usuarioClient = usuarioClient;
    }

    public Page<OutputCategoria> exibirTodas(Pageable pagination) {
        LOGGER.info("Buscando todas as categorias...");
        return this.repositorio.findAll(pagination).map(OutputCategoria::new);
    }

    public OutputCategoria exibirCategoriaPorId(UUID id) {
        LOGGER.info("Buscando categoria com ID -> {}", id);
        var categoria = buscarCategoriaPorId(id);

        return new OutputCategoria(categoria);
    }

    public Boolean categoriaEstaCadastrada(UUID id) {
        return this.repositorio.existsById(id);
    }

    public OutputCategoria criarCategoria(InputCategoria inputCategoria) {
        verificarSeUsuarioExiste(inputCategoria.usuarioId());

        var categoriaCriada = Categoria.novaCategoria(inputCategoria.nome(), inputCategoria.descricao(), inputCategoria.usuarioId());
        LOGGER.info("A categoria com o ID -> {} foi criada com sucesso!", categoriaCriada.getId());
        return new OutputCategoria(this.repositorio.save(categoriaCriada));
    }

    public OutputCategoria alterarNomeCategoria(UpdateNomeCategoria update) {
        var categoria = buscarCategoriaPorId(update.id());
        LOGGER.info("Alterando nome da categoria {} para {}...", categoria.getNome(), update.nome());
        categoria.modificarNome(update.nome());
        LOGGER.info("Nome da categoria {} alterada com sucesso para {}.", categoria.getNome(), update.nome());
        return new OutputCategoria(categoria);
    }

    public OutputCategoria alterarDescricaoCategoria(UpdateDescricaoCategoria update) {
        var categoria = buscarCategoriaPorId(update.id());
        LOGGER.info("Alterando descricao da categoria {}", categoria.getNome());
        categoria.modificarDescricao(update.descricao());
        LOGGER.info("Descricao da categoria {} alterada com sucesso!", categoria.getNome());
        return new OutputCategoria(categoria);
    }

    public void deletarCategoria(UUID id) {
        this.repositorio.deleteById(id);
    }

    private void verificarSeUsuarioExiste(UUID id) {
        LOGGER.info("Verificando se o usuário com o ID -> {} existe...", id);
        var resposta = this.usuarioClient.encontrarUsuarioPorId(id);
        if (Boolean.TRUE.equals(resposta)) {
            LOGGER.info("Usuário com o ID -> {} foi encontrado!", id);
            return;
        }
        LOGGER.error("Usuário com ID -> {} não encontrado!", id);
        throw new EntityNotFoundException("Usuário não foi encontrado!");
    }

    private Categoria buscarCategoriaPorId(UUID id) {
        LOGGER.info("Verificando se a categoria com o ID -> {} existe...", id);
        Optional<Categoria> categoria = this.repositorio.findById(id);

        if (categoria.isEmpty()) {
            LOGGER.error("Categoria com ID -> {} não foi encontrada", id);
            throw new EntityNotFoundException("Categoria não encontrada!");
        }
        LOGGER.info("A categoria {} com o ID -> {} existe!", categoria.get().getNome(), id);
        return categoria.get();
    }
}
