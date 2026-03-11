package com.henrique.Sistema.Assinatura.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.MetodoPagamento.MetodoPagamento;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.RetornoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assinatura_id")
    private Assinatura assinatura;

    @Column(name = "valor_pago", nullable = false)
    private BigDecimal valor;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "data_de_pagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_do_pagamento")
    private MetodoPagamento metodoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento")
    private RetornoPagamento retornoPagamento;

    public Pagamento() {
    }

    public Pagamento(Assinatura assinatura, BigDecimal valor, MetodoPagamento metodoPagamento, RetornoPagamento retornoPagamento) {
        this.assinatura = assinatura;
        this.valor = valor;
        this.dataPagamento = LocalDate.now();
        this.metodoPagamento = metodoPagamento;
        this.retornoPagamento = retornoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public RetornoPagamento getRetornoPagamento() {
        return retornoPagamento;
    }

    public void setRetornoPagamento(RetornoPagamento retornoPagamento) {
        this.retornoPagamento = retornoPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pagamento pagamento)) return false;
        return Objects.equals(getId(), pagamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
