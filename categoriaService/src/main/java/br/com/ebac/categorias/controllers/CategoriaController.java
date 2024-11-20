package br.com.ebac.categorias.controllers;

import br.com.ebac.categorias.adapter.InputCategoria;
import br.com.ebac.categorias.adapter.OutputCategoria;
import br.com.ebac.categorias.adapter.UpdateDescricaoCategoria;
import br.com.ebac.categorias.adapter.UpdateNomeCategoria;
import br.com.ebac.categorias.services.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<OutputCategoria>> buscarCategorias(@ParameterObject @PageableDefault(sort = "nome") Pageable pagination) {
        return ResponseEntity.ok(this.service.exibirTodas(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputCategoria> buscarCategoriaPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(this.service.exibirCategoriaPorId(id));
    }

    @GetMapping("/{id}/is-cadastrado")
    public ResponseEntity<Boolean> verificaSeCatagoriaEstaCadastrado(@PathVariable UUID id) {
        return ResponseEntity.ok(this.service.categoriaEstaCadastrada(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<OutputCategoria> novaCategoria(@RequestBody @Valid InputCategoria input) {
        var categoriaCriada = this.service.criarCategoria(input);
        return ResponseEntity.created(URI.create("/categorias/" + categoriaCriada.id())).body(categoriaCriada);
    }

    @PutMapping("/alterar-nome")
    @Transactional
    public ResponseEntity<OutputCategoria> atualizarNomeCategoria(@RequestBody @Valid UpdateNomeCategoria update) {
        return ResponseEntity.ok(this.service.alterarNomeCategoria(update));
    }

    @PutMapping("/alterar-descricao")
    @Transactional
    public ResponseEntity<OutputCategoria> atualizarDescricaoCategoria(@RequestBody @Valid UpdateDescricaoCategoria update) {
        return ResponseEntity.ok(this.service.alterarDescricaoCategoria(update));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> excluirCategoria(@PathVariable UUID id) {
        this.service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
