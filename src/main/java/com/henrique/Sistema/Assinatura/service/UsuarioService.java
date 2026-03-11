package com.henrique.Sistema.Assinatura.service;

import com.henrique.Sistema.Assinatura.DTOs.UsuarioRequestDTO;
import com.henrique.Sistema.Assinatura.entity.Usuario;
import com.henrique.Sistema.Assinatura.exceptions.BadRequestException;
import com.henrique.Sistema.Assinatura.exceptions.ConflictRequestException;
import com.henrique.Sistema.Assinatura.exceptions.NotFoundException;
import com.henrique.Sistema.Assinatura.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class.getName());
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAllUsers (){
        logger.info("Finding all Users");
        return usuarioRepository.findAll();
    }

    public Usuario findByUser(Long id){
        logger.info("Looking for a User");
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return usuarioRepository.save(usuario);
    }

    public Usuario createUser (Usuario user){
        if (user.getNome() == null || user.getNome().isBlank()){
            throw new BadRequestException("Required one name");
        }
        if (usuarioRepository.existsByEmail(user.getEmail())){
            throw new ConflictRequestException("Email already registered");
        }
        Usuario usuario = new Usuario(
                user.getNome(),
                user.getEmail()
        );
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUser(Long id, Usuario user){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        usuarioRepository.delete(usuario);
    }
}
