package com.henrique.Sistema.Assinatura.mappers.AssinaturaMapper;

import com.henrique.Sistema.Assinatura.DTOs.AssinaturaRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.AssinaturaResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class SignatureMapper {

    public Assinatura toEntity(AssinaturaRequestDTO dto) {

        Assinatura assinatura = new Assinatura();

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());

        Plano plano = new Plano();
        plano.setId(dto.getPlanoId());

        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);

        return assinatura;
    }

    public AssinaturaResponseDTO toResponse(Assinatura signature){
        AssinaturaResponseDTO dto = new AssinaturaResponseDTO();
        dto.setId(signature.getId());
        dto.setPlanoId(signature.getPlano().getId());
        dto.setUsuarioId(signature.getUsuario().getId());
        dto.setDataInicio(signature.getDataInicio());
        dto.setDataExpiracao(signature.getDataExpiracao());
        dto.setProximaCobranca(signature.getProximaCobranca());
        dto.setStatusAssinatura(signature.getStatusAssinatura());
        dto.setDataCancelamento(signature.getDataCancelamento());
        return dto;
    }
}
