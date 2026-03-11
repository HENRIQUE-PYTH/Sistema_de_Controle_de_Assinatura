package com.henrique.Sistema.Assinatura.controller;

import com.henrique.Sistema.Assinatura.DTOs.PlanoRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.PlanoResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.mappers.PlanoMapper.PlansMapper;
import com.henrique.Sistema.Assinatura.service.PlanoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService planoService;
    private final PlansMapper plansMapper;

    public PlanoController(PlanoService planoService, PlansMapper plansMapper) {
        this.planoService = planoService;
        this.plansMapper = plansMapper;
    }

    @GetMapping
    public List<PlanoResponseDTO> findAll (){
        return planoService.findAllPlans()
                .stream()
                .map(plansMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> findBYPlans(@PathVariable Long id){
        Plano plano = planoService.findByPlans(id);
        return ResponseEntity.ok(plansMapper.toResponse(plano));
    }

    @PostMapping
    public ResponseEntity<PlanoResponseDTO> createPlans (@RequestBody PlanoRequestDTO dto){
        Plano plans = plansMapper.toEntity(dto);
        Plano plano = planoService.createPlans(plans);
        return ResponseEntity.ok(plansMapper.toResponse(plano));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoResponseDTO> updatePlans (
            @PathVariable Long id, @RequestBody PlanoRequestDTO dto){
        Plano plano = plansMapper.toEntity(dto);
        Plano plans = planoService.updatePlans(id, plano);
        return ResponseEntity.ok(plansMapper.toResponse(plans));
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activatePlans (@PathVariable Long id){
        planoService.acivatePlan(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivatePlans (@PathVariable Long id){
        planoService.deactivePlan(id);
        return ResponseEntity.noContent().build();
    }
}
