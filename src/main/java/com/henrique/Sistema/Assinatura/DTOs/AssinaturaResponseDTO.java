package com.henrique.Sistema.Assinatura.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import com.henrique.Sistema.Assinatura.entity.Usuario;

import java.time.LocalDate;

public class AssinaturaResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long planoId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataExpiracao;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate proximaCobranca;

    private StatusAssinatura statusAssinatura;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataCancelamento;


    public AssinaturaResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPlanoId() {
        return planoId;
    }

    public void setPlanoId(Long planoId) {
        this.planoId = planoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public LocalDate getProximaCobranca() {
        return proximaCobranca;
    }

    public void setProximaCobranca(LocalDate proximaCobranca) {
        this.proximaCobranca = proximaCobranca;
    }

    public StatusAssinatura getStatusAssinatura() {
        return statusAssinatura;
    }

    public void setStatusAssinatura(StatusAssinatura statusAssinatura) {
        this.statusAssinatura = statusAssinatura;
    }

    public LocalDate getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDate dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

}

