package br.com.ebac.memes.repositories;


import br.com.ebac.memes.entities.Meme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemeRepositorio extends JpaRepository<Meme, UUID> {
}
