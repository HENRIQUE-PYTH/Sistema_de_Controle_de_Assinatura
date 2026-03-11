package com.henrique.Sistema.Assinatura.controller;

import com.henrique.Sistema.Assinatura.DTOs.PagamentoRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.PagamentoResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Pagamento;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.MetodoPagamento.MetodoPagamento;
import com.henrique.Sistema.Assinatura.mappers.PagamentoMapper.PaymentMapper;
import com.henrique.Sistema.Assinatura.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final PaymentMapper paymentMapper;

    public PagamentoController(PagamentoService pagamentoService, PaymentMapper paymentMapper) {
        this.pagamentoService = pagamentoService;
        this.paymentMapper = paymentMapper;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PagamentoResponseDTO> findByPayment(@PathVariable Long id){
        Pagamento pagamento = pagamentoService.findBYPagamento(id);
        return ResponseEntity.ok(paymentMapper.toResponse(pagamento));
    }

    @GetMapping("/assinaturas/{assinaturaId}")
    public ResponseEntity<List<PagamentoResponseDTO>> payments(@PathVariable Long assinaturaId){
        List<PagamentoResponseDTO> response = pagamentoService.pagamentos(assinaturaId)
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/assinaturas/{assinaturaId}/pagamentos")
    public ResponseEntity<PagamentoResponseDTO> makePayment (@PathVariable Long assinaturaId,
                                                             @RequestParam MetodoPagamento metodoPagamento,
                                                             @RequestBody PagamentoRequestDTO dto){
        Pagamento pagamento = pagamentoService.realizarPagamento(assinaturaId, metodoPagamento, dto);
        return ResponseEntity.ok(paymentMapper.toResponse(pagamento));
    }


}
