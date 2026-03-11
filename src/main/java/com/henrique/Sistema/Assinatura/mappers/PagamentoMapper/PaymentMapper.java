package com.henrique.Sistema.Assinatura.mappers.PagamentoMapper;

import com.henrique.Sistema.Assinatura.DTOs.PagamentoRequestDTO;
import com.henrique.Sistema.Assinatura.DTOs.PagamentoResponseDTO;
import com.henrique.Sistema.Assinatura.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Pagamento toEntity (PagamentoRequestDTO dto){
        Pagamento pay = new Pagamento();
        pay.setValor(dto.getValor());
        return pay;
    }

    public PagamentoResponseDTO toResponse(Pagamento pay){
        PagamentoResponseDTO dto = new PagamentoResponseDTO();
        dto.setId(pay.getId());
        dto.setAssinaturaId(pay.getAssinatura().getId());
        dto.setValor(pay.getValor());
        dto.setDataPagamento(pay.getDataPagamento());
        dto.setMetodoPagamento(pay.getMetodoPagamento());
        return dto;
    }
}
