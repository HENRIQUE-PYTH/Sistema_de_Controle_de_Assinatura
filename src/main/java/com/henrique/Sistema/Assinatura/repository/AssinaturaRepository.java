package com.henrique.Sistema.Assinatura.repository;

import com.henrique.Sistema.Assinatura.entity.Assinatura;
import com.henrique.Sistema.Assinatura.entity.RetornoAssinatura.StatusAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    Optional<Assinatura> findByUsuarioIdAndStatusAssinatura(Long id, StatusAssinatura status);
    List<Assinatura> findByUsuarioIdOrderByDataInicioDesc(Long usuarioId);

    List<Assinatura> findByStatusAssinaturaAndProximaCobrancaLessThanEqual(
            StatusAssinatura status,
            LocalDate data
            );
}