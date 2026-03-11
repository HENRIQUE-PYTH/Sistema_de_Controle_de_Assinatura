package com.henrique.Sistema.Assinatura.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henrique.Sistema.Assinatura.entity.TipoDoPlano.PlanoTipo;
import java.time.LocalDate;

public class PlanoResponseDTO {

    private Long id;
    private PlanoTipo planoTipo;
    private Boolean ativo;

    @JsonFormat(pattern = "dd-MM-YYYY")
    private LocalDate dataCriacao;

    public PlanoResponseDTO() {
    }

    public PlanoResponseDTO(PlanoTipo planoTipo, Boolean ativo) {
        this.planoTipo = planoTipo;
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

    public void setPlanoTipo(PlanoTipo planoTipo) {
        this.planoTipo = planoTipo;
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
}
