package br.com.ebac.categorias.adapter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateDescricaoCategoria(
        @NotNull
        UUID id,
        @NotEmpty
        @NotBlank
        String descricao
) {
}
