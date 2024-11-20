package br.com.ebac.usuarios.services;

import br.com.ebac.usuarios.adapter.InputUsuario;
import br.com.ebac.usuarios.adapter.OutputUsuario;
import br.com.ebac.usuarios.adapter.UpdateEmailUsuario;
import br.com.ebac.usuarios.entities.Usuario;
import br.com.ebac.usuarios.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private UsuarioRepository repositorio;

    @Autowired
    public UsuarioService(UsuarioRepository repositorio) {
        this.repositorio = repositorio;
    }

    public OutputUsuario novoUsuario(InputUsuario inputUsuario) {
        LOGGER.info("Criando o usuário {}", inputUsuario.nome());
        Usuario novoUsuario = Usuario.criarUsuario(inputUsuario.nome(), inputUsuario.email());
        LOGGER.info("Usuário {} criado com sucesso!", inputUsuario.nome());
        return new OutputUsuario(this.repositorio.save(novoUsuario));
    }

    public Page<OutputUsuario> exibirTodosUsuarios(Pageable pagination) {
        LOGGER.info("Buscando todos os usuários...");
        return this.repositorio.findAll(pagination).map(OutputUsuario::new);
    }

    public OutputUsuario exibirUsuarioPorId(UUID id) {
        LOGGER.info("Buscando usuário com ID - {}", id);
        var usuario = verificarSeUsuarioExiste(id);
        LOGGER.info("Usuário com ID {} encontrado!", id);
        return new OutputUsuario(usuario);
    }

    public Boolean usuarioEstaCadastrado(UUID id) {
        return this.repositorio.existsById(id);
    }

    public OutputUsuario modificarEmail(UpdateEmailUsuario update) {
        var usuario = verificarSeUsuarioExiste(update.id());
        LOGGER.info("Modificando e-mail {} para {}", usuario.getEmail(), update.email());
        usuario.alterarEmail(update.email());
        LOGGER.info("E-mail {} alterado com sucesso para {}", usuario.getEmail(), update.email());
        return new OutputUsuario(usuario);
    }

    public void deletarUsuario(UUID id) {
        this.repositorio.deleteById(id);
    }

    private Usuario verificarSeUsuarioExiste(UUID id) {
        LOGGER.info("Verificando se usuário com o ID -> {} existe...", id);
        Optional<Usuario> usuario = this.repositorio.findById(id);

        if (usuario.isEmpty()) {
            LOGGER.error("Usuário com o ID -> {} não foi encontrado!", id);
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return usuario.get();
    }
}
