package com.henrique.Sistema.Assinatura.controller;

import com.henrique.Sistema.Assinatura.DTOs.UsuarioRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.UsuarioResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Usuario;
import com.henrique.Sistema.Assinatura.mappers.UsuarioMapper.UserMapper;
import com.henrique.Sistema.Assinatura.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UserMapper userMapper;

    public UsuarioController(UsuarioService usuarioService, UserMapper userMapper) {
        this.usuarioService = usuarioService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UsuarioResponseDTO> findAll(){
        return usuarioService.findAllUsers()
                .stream()
                .map(userMapper::ToResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findBYUser(@PathVariable Long id){
        Usuario usuario = usuarioService.findByUser(id);
        return ResponseEntity.ok(userMapper.ToResponse(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> createUser (@RequestBody UsuarioRequestDTO dto){
        Usuario usuario = userMapper.toEntity(dto);
        Usuario user = usuarioService.createUser(usuario);
        return ResponseEntity.ok(userMapper.ToResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO dto){
        Usuario usuario = userMapper.toEntity(dto);
        Usuario user = usuarioService.updateUser(id, usuario);
        return ResponseEntity.ok(userMapper.ToResponse(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id){
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


}
