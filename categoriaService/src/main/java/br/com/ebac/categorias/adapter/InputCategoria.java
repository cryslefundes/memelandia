package br.com.ebac.categorias.adapter;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record InputCategoria(
        @NotBlank
        @NotEmpty
        String nome,
        @NotBlank
        @NotEmpty
        String descricao,
        @NotNull
        @JsonAlias("usuario_id")
        UUID usuarioId
) {
}
