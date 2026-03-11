package com.henrique.Sistema.Assinatura.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henrique.Sistema.Assinatura.entity.TipoDoPlano.PlanoTipo;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_plano")
    private PlanoTipo planoTipo;

    @Column(name = "valor_planos")
    private BigDecimal valor;

    @Column(name = "ativo")
    private Boolean ativo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "criacao_do_plano")
    private LocalDate dataCriacao;

    @OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
    private List<Assinatura> assinaturas;

    public Plano() {
    }

    public Plano(PlanoTipo planoTipo, BigDecimal valor, Boolean ativo) {
        this.planoTipo = planoTipo;
        this.valor = valor;
        this.ativo = ativo;
        this.dataCriacao = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanoTipo getPlanoTipo() {
        return planoTipo;
    }

    public void setPlanoTipo(PlanoTipo status) {
        this.planoTipo = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<Assinatura> getAssinaturas() {
        return assinaturas;
    }

    public void setAssinaturas(List<Assinatura> assinaturas) {
        this.assinaturas = assinaturas;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Plano plano)) return false;
        return Objects.equals(getId(), plano.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
