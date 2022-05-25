package com.nutrihouse.app.domain;

import com.nutrihouse.app.repositories.ProdutoRepository;
import com.nutrihouse.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ControleDeEstoque {

    @Autowired
    ProdutoRepository repo;

    public void saidaDeEstoque(Pedido pedido) {
        for (ItensPedido itens : pedido.getItensPedidos()) {
            Produto produto = itens.getProduto();
            produto.setQuantidade(produto.getQuantidade() - itens.getQuantidade());
            repo.save(produto);
        }
    }

}

