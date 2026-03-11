package com.henrique.Sistema.Assinatura.controller;

import com.henrique.Sistema.Assinatura.DTOs.AssinaturaResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.mappers.AssinaturaMapper.SignatureMapper;
import com.henrique.Sistema.Assinatura.service.AssinaturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService assinaturaService;
    private final SignatureMapper signatureMapper;

    public AssinaturaController(AssinaturaService assinaturaService, SignatureMapper signatureMapper) {
        this.assinaturaService = assinaturaService;
        this.signatureMapper = signatureMapper;
    }

    @GetMapping
    public List<AssinaturaResponseDTO> findAll(){
        return assinaturaService.findAllSignature()
                .stream()
                .map(signatureMapper::toResponse)
                .toList();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AssinaturaResponseDTO> findByUsuarioIdOrderByDataInicioDesc(@PathVariable Long usuarioId){
        return assinaturaService.findByUsuarioIdOrderByDataInicioDesc(usuarioId)
                .stream()
                .map(signatureMapper::toResponse)
                .toList();
    }

    @GetMapping("/usuario/{usuarioId}/active")
    public ResponseEntity<AssinaturaResponseDTO> findActiveByUser (@PathVariable Long usuarioId){
        Assinatura assinatura = assinaturaService.findActiveByUser(usuarioId);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }

    @GetMapping("/{assinaturaId}")
    public ResponseEntity<AssinaturaResponseDTO> findBySignature (@PathVariable Long assinaturaId){
        Assinatura assinatura = assinaturaService.findBySignature(assinaturaId);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }

    @PostMapping("/usuario/{usuarioId}/plano/{planoId}")
    public ResponseEntity<AssinaturaResponseDTO> createSignature(@PathVariable Long usuarioId,
                                                                 @PathVariable Long planoId){
        Assinatura assinatura = assinaturaService.createSignature(planoId, usuarioId);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }

    @PostMapping("/{assinaturaId}/change-plan/{novoPlanoId}")
    public ResponseEntity<AssinaturaResponseDTO> changPlan (@PathVariable Long assinaturaId,
                                                            @PathVariable Long novoPlanoId){
        Assinatura assinatura = assinaturaService.changePlan(assinaturaId, novoPlanoId);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }

    @PatchMapping("/{assinaturaId}/renew")
    public ResponseEntity<AssinaturaResponseDTO> renewSignature (@PathVariable Long assinaturaId){
        Assinatura assinatura = assinaturaService.renewSignature(assinaturaId);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }

    @PatchMapping("/{assinaturaId}/cancel")
    public ResponseEntity<Void> cancelSignature (@PathVariable Long assinaturaId){
        assinaturaService.cancelSignature(assinaturaId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reactivate")
    public ResponseEntity<AssinaturaResponseDTO> reactivateSignature (@PathVariable Long id){
        Assinatura assinatura = assinaturaService.reactivateSignature(id);
        return ResponseEntity.ok(signatureMapper.toResponse(assinatura));
    }


}

