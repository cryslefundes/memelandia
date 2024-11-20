package br.com.ebac.memes.services;

import br.com.ebac.memes.adapter.InputMeme;
import br.com.ebac.memes.adapter.OutputMeme;
import br.com.ebac.memes.adapter.UpdateMeme;
import br.com.ebac.memes.client.CategoriaClient;
import br.com.ebac.memes.client.UsuarioClient;
import br.com.ebac.memes.entities.Meme;
import br.com.ebac.memes.repositories.MemeRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MemeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemeService.class);
    private final UsuarioClient usuarioClient;
    private final CategoriaClient categoriaClient;
    private final MemeRepositorio repositorio;

    public MemeService(MemeRepositorio repositorio, UsuarioClient usuarioClient, CategoriaClient categoriaClient) {
        this.repositorio = repositorio;
        this.usuarioClient = usuarioClient;
        this.categoriaClient = categoriaClient;
    }

    public Page<OutputMeme> exibirTodosOsMemes(Pageable pagination) {
        LOGGER.info("Buscando todos os memes...");
        return this.repositorio.findAll(pagination).map(OutputMeme::new);
    }

    public OutputMeme exibirMemePorId(UUID id) {
        LOGGER.info("Buscando um meme com o ID -> {}", id);
        var meme = buscarMemePorId(id);
        return new OutputMeme(meme);
    }

    public OutputMeme criarMeme(InputMeme input) {
        verificarSeExisteUsuario(input.usuarioId());
        verificarSeCategoriaExiste(input.categoriaId());

        var memeCriado = Meme.novoMeme(input.nome(), input.descricao(), input.url(), input.categoriaId(), input.usuarioId());
        LOGGER.info("Meme com o ID -> {} foi criado com sucesso!", memeCriado.getId());
        return new OutputMeme(this.repositorio.save(memeCriado));
    }

    public OutputMeme alterarMeme(UpdateMeme update) {
        verificarSeCategoriaExiste(update.categoriaId());
        var meme = buscarMemePorId(update.id());

        meme.modificarNome(update.nome());
        meme.modificarDescricao(update.descricao());
        meme.modificarUrl(update.url());
        meme.modificarCategoria(update.categoriaId());

        return new OutputMeme(meme);
    }

    public void deletarMeme(UUID id) {
        this.repositorio.deleteById(id);
    }

    private void verificarSeCategoriaExiste(UUID id) {
         LOGGER.info("Verificando se a categoria com o ID -> {} existe...", id);
        var resposta = this.categoriaClient.encontrarCategoriaPorId(id);
        if (Boolean.TRUE.equals(resposta)) {
            LOGGER.info("Categoria com o ID -> {} foi encontrada!", id);
            return;
        }

        LOGGER.error("Categoria com o ID -> {} não foi encontrada!", id);
        throw new EntityNotFoundException("Categoria não foi encontrada!");
    }

    private void verificarSeExisteUsuario(UUID id) {
        LOGGER.info("Verificando se o usuário com o ID -> {} existe...", id);
        var resposta = this.usuarioClient.encontrarUsuarioPorId(id);
        if (Boolean.TRUE.equals(resposta)) {
            LOGGER.info("Usuário com o ID -> {} foi encontrado!", id);
            return;
        }

        LOGGER.error("Usuário com o ID -> {} não foi encontrado!", id);
        throw new EntityNotFoundException("Usuário não foi encontrado!");
    }

    private Meme buscarMemePorId(UUID id) {
        LOGGER.info("Verificando se o meme com o ID -> {} existe...", id);
        Optional<Meme> meme = this.repositorio.findById(id);
        if (meme.isEmpty()) {
            LOGGER.error("Meme com o ID -> {} não foi encontrado", id);
            throw new EntityNotFoundException("Meme não encontrado!");
        }
        LOGGER.info("O meme {} com o ID -> {} existe!", meme.get().getNome(), id);
        return meme.get();
    }

}
