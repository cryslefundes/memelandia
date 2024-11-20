package br.com.ebac.memes.adapter;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record InputMeme(
        @NotEmpty
        @NotBlank
        String nome,
        @NotEmpty
        @NotBlank
        String descricao,
        @NotEmpty
        @NotBlank
        String url,
        @NotNull
        @JsonAlias("categoria_id")
        UUID categoriaId,
        @NotNull
        @JsonAlias("usuario_id")
        UUID usuarioId
) {
}
