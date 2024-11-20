package br.com.ebac.categorias.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Instant dataCadastro;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    public Categoria() {}

    private Categoria(UUID id, String nome, String descricao, Instant dataCadastro, UUID usuarioId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.usuarioId = usuarioId;
    }

    public static Categoria novaCategoria(String nome, String descricao, UUID usuarioId) {
        return new Categoria(UUID.randomUUID(), nome, descricao, Instant.now(), usuarioId);
    }

    public void modificarNome(String nome) {
        this.nome = nome;
    }

    public void modificarDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getUsuarioId() {
        return this.usuarioId;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

}
