package br.com.ebac.usuarios.repositories;

import br.com.ebac.usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
