package br.com.ebac.usuarios.adapter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateEmailUsuario(
        @NotNull
        UUID id,
        @NotBlank
        @NotEmpty
        String email
) {
}
