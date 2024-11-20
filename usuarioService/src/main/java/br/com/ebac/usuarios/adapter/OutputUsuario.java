package br.com.ebac.usuarios.adapter;

import br.com.ebac.usuarios.entities.Usuario;

import java.time.Instant;
import java.util.UUID;

public record OutputUsuario(
        UUID id,
        String nome,
        String email,
        Instant dataCadastro
) {
    public OutputUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro()
        );
    }
}
