package com.henrique.Sistema.Assinatura.ConfigScheduler;

import com.henrique.Sistema.Assinatura.entity.StatusPagamento.MetodoPagamento.MetodoPagamento;
import com.henrique.Sistema.Assinatura.service.PagamentoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CobrancaScheduler {

    private final PagamentoService pagamentoService;

    public CobrancaScheduler(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @Scheduled(cron = "0 0 2 * * *") // todo dia às 02:00
    public void executarCobrancaAutomatica(){
        pagamentoService.cobrancaAutomatica(MetodoPagamento.CARTAO);
    }
}
