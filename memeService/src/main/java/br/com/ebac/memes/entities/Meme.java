package br.com.ebac.memes.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "memes")
public class Meme {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String url;

    @Column(name = "data_cadastro", nullable = false)
    private Instant dataCadastro;

    @Column(name = "categoria_id", nullable = false)
    private UUID categoriaId;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    public Meme() {}

    private Meme(UUID id, String nome, String descricao, String url, Instant dataCadastro, UUID categoriaId, UUID usuarioId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.url = url;
        this.dataCadastro = dataCadastro;
        this.categoriaId = categoriaId;
        this.usuarioId = usuarioId;
    }

    public static Meme novoMeme(String nome, String descricao, String url, UUID categoriaId, UUID usuarioId) {
        return new Meme(UUID.randomUUID(), nome, descricao, url, Instant.now(), categoriaId, usuarioId);
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }

    public void modificarNome(String nome) {
        this.nome = nome;
    }

    public void modificarDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void modificarCategoria(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void modificarUrl(String url) {
        this.url = url;
    }
}
