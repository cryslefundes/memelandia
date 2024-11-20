package br.com.ebac.usuarios.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(name = "data_cadastro")
    private Instant dataCadastro;

    public Usuario() {}

    private Usuario(UUID id, String nome, String email, Instant dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCadastro = dataCadastro;
    }

    public static Usuario criarUsuario(String nome, String email) {
        var id = UUID.randomUUID();
        var dataCadastro = Instant.now();
        return new Usuario(id, nome, email, dataCadastro);
    }

    public void alterarEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

}
