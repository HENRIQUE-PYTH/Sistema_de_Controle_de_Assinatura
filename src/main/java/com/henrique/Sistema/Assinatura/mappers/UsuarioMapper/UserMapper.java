package com.henrique.Sistema.Assinatura.mappers.UsuarioMapper;

import com.henrique.Sistema.Assinatura.DTOs.UsuarioRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.UsuarioResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserMapper {

    public Usuario toEntity (UsuarioRequestDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }

    public UsuarioResponseDTO ToResponse(Usuario usuario){
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setDataCadastro(LocalDate.now());
        return dto;
    }
}
