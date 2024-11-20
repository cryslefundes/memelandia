package br.com.ebac.categorias.adapter;

import br.com.ebac.categorias.entities.Categoria;

import java.time.Instant;
import java.util.UUID;

public record OutputCategoria(
        UUID id,
        String nome,
        String descricao,
        Instant dataCadastro,
        UUID usuarioId
) {
    public OutputCategoria(Categoria categoria){
        this(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.getDataCadastro(),
                categoria.getUsuarioId()
        );
    }
}
