package com.henrique.Sistema.Assinatura.DTOs;

import java.math.BigDecimal;

public class PagamentoRequestDTO {

    private BigDecimal valor;

    public PagamentoRequestDTO() {
    }

    public PagamentoRequestDTO(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
