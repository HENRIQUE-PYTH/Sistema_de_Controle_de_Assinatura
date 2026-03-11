package com.henrique.Sistema.Assinatura.repository;

import com.henrique.Sistema.Assinatura.entity.Plano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long> {
}
