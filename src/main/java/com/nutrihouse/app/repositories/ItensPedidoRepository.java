package com.nutrihouse.app.repositories;

import com.nutrihouse.app.domain.ItensPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
}
