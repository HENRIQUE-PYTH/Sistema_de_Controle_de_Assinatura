package com.henrique.Sistema.Assinatura.DTOs;


import com.henrique.Sistema.Assinatura.entity.TipoDoPlano.PlanoTipo;


import java.math.BigDecimal;


public class PlanoRequestDTO {

    private PlanoTipo planoTipo;
    private BigDecimal valor;

    public PlanoRequestDTO() {
    }

    public PlanoRequestDTO(PlanoTipo planoTipo, BigDecimal valor) {
        this.planoTipo = planoTipo;
        this.valor = valor;
    }

    public PlanoTipo getPlanoTipo() {
        return planoTipo;
    }

    public void setPlanoTipo(PlanoTipo planoTipo) {
        this.planoTipo = planoTipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
