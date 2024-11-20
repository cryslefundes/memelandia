package br.com.ebac.categorias.repositories;

import br.com.ebac.categorias.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepositorio extends JpaRepository<Categoria, UUID> {
}
