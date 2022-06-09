package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.ItensPedido;
import com.nutrihouse.app.domain.Pedido;
import com.nutrihouse.app.repositories.ItensPedidoRepository;
import com.nutrihouse.app.repositories.PedidoRepository;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoService implements Serializable {

    @Autowired
    PedidoRepository repo;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ItensPedidoRepository itensPedidoRepository;


    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Id nao encontrado!" + id));
    }


    public Page<Pedido> findPages(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.setLocalDateTime(LocalDateTime.now());
        repo.save(pedido);

        for (ItensPedido ip : pedido.getItensPedidos()) {
            ip.setPreco(ip.getPreco());
            ip.setQuantidade(ip.getQuantidade());
            ip.setPedido(pedido);
        }
        itensPedidoRepository.saveAll(pedido.getItensPedidos());
        return pedido;
    }


}
