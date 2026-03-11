package com.henrique.Sistema.Assinatura.repository;

import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.entity.Pagamento;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import com.henrique.Sistema.Assinatura.entity.StatusPagamento.RetornoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByAssinaturaIdOrderByDataPagamentoDesc(Long assinaturaId);
    Long countByAssinaturaIdAndRetornoPagamento(Long id, RetornoPagamento retornoPagamento);

}
