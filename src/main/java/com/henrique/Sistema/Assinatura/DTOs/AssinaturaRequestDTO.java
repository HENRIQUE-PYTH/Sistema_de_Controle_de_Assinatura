package com.henrique.Sistema.Assinatura.DTOs;


public class AssinaturaRequestDTO {

    private Long usuarioId;
    private Long planoId;

    public AssinaturaRequestDTO(Long usuarioId, Long planoId) {
        this.usuarioId = usuarioId;
        this.planoId = planoId;
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
}
