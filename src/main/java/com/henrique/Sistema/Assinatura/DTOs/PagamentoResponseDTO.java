package com.henrique.Sistema.Assinatura.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.MetodoPagamento.MetodoPagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoResponseDTO {

    private Long id;
    private Long assinaturaId;
    private BigDecimal valor;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataPagamento;
    private MetodoPagamento metodoPagamento;

    public PagamentoResponseDTO() {
    }

    public PagamentoResponseDTO(BigDecimal valor, LocalDate dataPagamento, MetodoPagamento metodoPagamento) {
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.metodoPagamento = metodoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssinaturaId() {
        return assinaturaId;
    }

    public void setAssinaturaId(Long assinaturaId) {
        this.assinaturaId = assinaturaId;
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
}
