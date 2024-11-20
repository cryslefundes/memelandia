package br.com.ebac.memes.adapter;

import br.com.ebac.memes.entities.Meme;

import java.time.Instant;
import java.util.UUID;

public record OutputMeme(
        UUID id,
        String nome,
        String descricao,
        String url,
        Instant dataCadastro,
        UUID categoriaId,
        UUID usuarioId
) {
    public OutputMeme(Meme meme){
        this(
                meme.getId(),
                meme.getNome(),
                meme.getDescricao(),
                meme.getUrl(),
                meme.getDataCadastro(),
                meme.getCategoriaId(),
                meme.getUsuarioId()
        );
    }
}
