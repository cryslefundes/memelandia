package br.com.ebac.usuarios.adapter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record InputUsuario(
        @NotBlank
        @NotEmpty
        String nome,
        @Email
        @NotNull
        @NotEmpty
        @NotBlank
        String email
) {
}
