package br.com.ebac.memes.controllers;

import br.com.ebac.memes.adapter.InputMeme;
import br.com.ebac.memes.adapter.OutputMeme;
import br.com.ebac.memes.adapter.UpdateMeme;
import br.com.ebac.memes.services.MemeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/memes")
public class MemeController {
    private MemeService service;

    public MemeController(MemeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OutputMeme>> buscarTodosOsMemes(@ParameterObject @PageableDefault(sort = "nome") Pageable pagination) {
        return ResponseEntity.ok(this.service.exibirTodosOsMemes(pagination));
    }

    @GetMapping("/id")
    public ResponseEntity<OutputMeme> buscarMemePorId(UUID id) {
        return ResponseEntity.ok(this.service.exibirMemePorId(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputMeme> novoMeme(@RequestBody @Valid InputMeme input) {
        var memeCriado = this.service.criarMeme(input);
        return ResponseEntity.created(URI.create("/memes/" + memeCriado.id())).body(memeCriado);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<OutputMeme> atualizarMeme(@RequestBody @Valid UpdateMeme update) {
        return ResponseEntity.ok(this.service.alterarMeme(update));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluirMeme(@PathVariable UUID id) {
        this.service.deletarMeme(id);
        return ResponseEntity.noContent().build();
    }
}
