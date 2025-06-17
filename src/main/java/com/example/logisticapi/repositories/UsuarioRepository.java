package com.example.logisticapi.repositories;

import com.example.logisticapi.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Encontra um usuário pelo email. Útil para login.
    Optional<Usuario> findByEmail(String email);
}