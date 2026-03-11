package com.henrique.Sistema.Assinatura.repository;

import com.henrique.Sistema.Assinatura.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);
}
