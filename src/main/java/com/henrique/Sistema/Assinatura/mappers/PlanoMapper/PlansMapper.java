package com.henrique.Sistema.Assinatura.mappers.PlanoMapper;

import com.henrique.Sistema.Assinatura.DTOs.PlanoRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.PlanoResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Plano;
import org.springframework.stereotype.Component;

@Component
public class PlansMapper {

    public PlanoResponseDTO toResponse (Plano plano){
        PlanoResponseDTO dto = new PlanoResponseDTO();
        dto.setId(plano.getId());
        dto.setPlanoTipo(plano.getPlanoTipo());
        dto.setAtivo(plano.getAtivo());
        dto.setDataCriacao(plano.getDataCriacao());
        return dto;
    }

    public Plano toEntity (PlanoRequestDTO dto){
        Plano plano = new Plano();
        plano.setPlanoTipo(dto.getPlanoTipo());
        plano.setValor(dto.getValor());
        return plano;
    }
}
