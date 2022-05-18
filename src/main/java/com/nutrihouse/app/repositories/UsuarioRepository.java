package com.nutrihouse.app.repositories;

import com.nutrihouse.app.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
