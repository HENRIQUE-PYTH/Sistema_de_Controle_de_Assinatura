package com.henrique.Sistema.Assinatura.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomes", length = 250, nullable = false)
    private String nome;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "data_de_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Assinatura> assinaturas;


    public Usuario() {
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.dataCadastro = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId()) && Objects.equals(getEmail(), usuario.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail());
    }
}
