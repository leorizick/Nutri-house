package com.nutrihouse.app.service;

import com.nutrihouse.app.domain.Cliente;
import com.nutrihouse.app.domain.ItensPedido;
import com.nutrihouse.app.domain.Receituario;
import com.nutrihouse.app.repositories.ItensPedidoRepository;
import com.nutrihouse.app.repositories.ReceituarioRepository;
import com.nutrihouse.app.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReceituarioService implements Serializable {

    @Autowired
    ReceituarioRepository repo;

    @Autowired
    ClienteService clienteService;

    @Autowired
    ItensPedidoRepository itensPedidoRepository;

    public Receituario find(Integer id){
        Optional<Receituario> receituario= repo.findById(id);
        return receituario.orElseThrow(() -> new ObjectNotFoundException("Receita nao encontrada! Id:" + id));
    }

    public Page<Receituario> findPages(Pageable pageable){
        return repo.findAll(pageable);
    }

    public Receituario save(Receituario receituario){
        receituario.setId(null);
        receituario.setDescricao(receituario.getDescricao());
        receituario.setCliente(clienteService.find(receituario.getCliente().getId()));
        if(receituario.getCliente().getPedido() != null) {
            receituario.setItensPedido(
                    receituario.getCliente().getPedido()
                            .get(receituario.getPedido().getId())
                            .getItensPedidos());
        }else receituario.setItensPedido(null);
        receituario.setLocalDateTime(LocalDateTime.now());
        repo.save(receituario);

        for (ItensPedido ip : receituario.getItensPedido()) {
            ip.setPreco(ip.getPreco());
            ip.setQuantidade(ip.getQuantidade());
            ip.setPedido(receituario.getPedido());
        }
        return receituario;
    }
}
