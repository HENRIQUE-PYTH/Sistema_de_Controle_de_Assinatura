package com.henrique.Sistema.Assinatura.entity.TipoDoPlano;


public enum PlanoTipo {

    MENSAL(1),
    ANUAL(12),
    PREMIUM(12);

    private final Integer duracaoMeses;

    PlanoTipo(Integer duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public Integer getDuracaoMeses() {
        return duracaoMeses;
    }

}
