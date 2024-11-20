package br.com.ebac.usuarios.controllers;

import br.com.ebac.usuarios.adapter.InputUsuario;
import br.com.ebac.usuarios.adapter.OutputUsuario;
import br.com.ebac.usuarios.adapter.UpdateEmailUsuario;
import br.com.ebac.usuarios.services.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OutputUsuario>> buscarUsuarios(@ParameterObject @PageableDefault(sort="nome") Pageable pagination) {
        return ResponseEntity.ok(this.service.exibirTodosUsuarios(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputUsuario> buscarUsuarioPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.service.exibirUsuarioPorId(id));
    }

    @GetMapping("/{id}/is-cadastrado")
    public ResponseEntity<Boolean> verificarSeUsuarioEstaCadastradoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.service.usuarioEstaCadastrado(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputUsuario> novoUsuario(@RequestBody @Valid InputUsuario inputUsuario) {
        var usuarioCadastrado = this.service.novoUsuario(inputUsuario);
        return ResponseEntity.created(URI.create("/usuarios/" + usuarioCadastrado.id())).body(usuarioCadastrado);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<OutputUsuario> atualizarEmail(@RequestBody @Valid UpdateEmailUsuario update) {
        return ResponseEntity.ok(this.service.modificarEmail(update));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluirUsuario(@PathVariable UUID id) {
        this.service.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
