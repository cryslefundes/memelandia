package br.com.ebac.memes.adapter;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateMeme(
    @NotNull
    UUID id,
    @NotBlank
    @NotEmpty
    String nome,
    @NotBlank
    @NotEmpty
    String descricao,
    @NotBlank
    @NotEmpty
    String url,
    @NotNull
    @JsonAlias("categoria_id")
    UUID categoriaId,
    @NotNull
    @JsonAlias("usuario_id")
    UUID usuarioId
) {
}
