package com.henrique.Sistema.Assinatura.service;

import com.henrique.Sistema.Assinatura.DTOs.PagamentoRequestDTO;
import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.entity.Pagamento;
import com.henrique.Sistema.Assinatura.entity.Plano;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.MetodoPagamento.MetodoPagamento;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.RetornoPagamento;
import com.henrique.Sistema.Assinatura.exceptions.BadRequestException;
import com.henrique.Sistema.Assinatura.exceptions.NoContentException;
import com.henrique.Sistema.Assinatura.exceptions.NotFoundException;
import com.henrique.Sistema.Assinatura.repository.AssinaturaRepository;
import com.henrique.Sistema.Assinatura.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, AssinaturaRepository assinaturaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    public Pagamento findBYPagamento(Long id){
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Payment not found"));
        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> pagamentos (Long assinaturaId){
        return pagamentoRepository.findByAssinaturaIdOrderByDataPagamentoDesc(assinaturaId);
    }


    public Pagamento realizarPagamento (Long assinaturaId,
                                        MetodoPagamento tipoPagamento,
                                        PagamentoRequestDTO dto){
        Assinatura assinatura = assinaturaRepository
                .findById(assinaturaId)
                .orElseThrow(() -> new NotFoundException("Signature not found"));

        if (!assinatura.getStatusAssinatura().equals(StatusAssinatura.ATIVA)){
            throw new BadRequestException("Signature is not activate");
        }

        Plano plano = assinatura.getPlano();

        Pagamento pagamento = new Pagamento();
        pagamento.setAssinatura(assinatura);
        pagamento.setMetodoPagamento(tipoPagamento);
        pagamento.setDataPagamento(LocalDate.now());
        pagamento.setValor(dto.getValor());

        Integer comparacao = dto.getValor().compareTo(plano.getValor());

        if (comparacao == 0) {
            pagamento.setRetornoPagamento(RetornoPagamento.APROVADO);
            LocalDate dataInicio = LocalDate.now();
            Integer mesesDuracao = plano.getPlanoTipo().getDuracaoMeses();
           assinatura.setDataExpiracao(dataInicio.plusMonths(mesesDuracao));
           assinatura.setProximaCobranca(dataInicio.plusMonths(1));
        }

        else {
            Long tentantivas = pagamentoRepository.countByAssinaturaIdAndRetornoPagamento(
                    assinaturaId,
                    RetornoPagamento.RECUSADO);

            if (tentantivas >= 2){
                pagamento.setRetornoPagamento(RetornoPagamento.BLOQUEADO);
                assinatura.setStatusAssinatura(StatusAssinatura.SUSPENSA);
            }
            else {
                pagamento.setRetornoPagamento(RetornoPagamento.RECUSADO);
            }
        }
        return pagamentoRepository.save(pagamento);
    }

    @Transactional
    public void cobrancaAutomatica(MetodoPagamento metodoPagamento){

        List<Assinatura> assinaturas = assinaturaRepository
                .findByStatusAssinaturaAndProximaCobrancaLessThanEqual(
                        StatusAssinatura.ATIVA,
                        LocalDate.now());

        for (Assinatura assinatura : assinaturas){

            Pagamento pagamento = new Pagamento();

            BigDecimal valorPlano = assinatura.getPlano().getValor();

            pagamento.setAssinatura(assinatura);
            pagamento.setValor(valorPlano);
            pagamento.setMetodoPagamento(metodoPagamento);
            pagamento.setDataPagamento(LocalDate.now());

            boolean pagamentoAprovado = true;

            if (pagamentoAprovado){

                pagamento.setRetornoPagamento(RetornoPagamento.APROVADO);

                Integer meses = assinatura.getPlano()
                        .getPlanoTipo()
                        .getDuracaoMeses();

                LocalDate novaBase = assinatura.getDataExpiracao().isAfter(LocalDate.now())
                        ? assinatura.getDataExpiracao()
                        : LocalDate.now();

                assinatura.setDataExpiracao(novaBase.plusMonths(meses));
                assinatura.setProximaCobranca(assinatura.getProximaCobranca().plusMonths(1));

            } else {

                Long tentativas = pagamentoRepository
                        .countByAssinaturaIdAndRetornoPagamento(
                                assinatura.getId(),
                                RetornoPagamento.RECUSADO);

                if (tentativas >= 2){
                    pagamento.setRetornoPagamento(RetornoPagamento.BLOQUEADO);
                    assinatura.setStatusAssinatura(StatusAssinatura.SUSPENSA);
                }
                else{
                    pagamento.setRetornoPagamento(RetornoPagamento.RECUSADO);
                }
            }

            pagamentoRepository.save(pagamento);
            assinaturaRepository.save(assinatura);
        }
    }


}
