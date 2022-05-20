package com.nutrihouse.app.domain;

import com.nutrihouse.app.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Optional;

@Component
public class ControleDeEstoque {

    @Autowired
    ProdutoRepository repo;

    public void saidaDeEstoque(Pedido pedido) {
        for (ItensPedido itens : pedido.getItensPedidos()) {
            Optional<Produto> obj = repo.findById(itens.getProduto().getId());
            Produto produto = optionalToProduto(obj);
            produto.setQuantidade(produto.getQuantidade() - itens.getQuantidade());
            repo.save(produto);
        }
    }

    public Produto optionalToProduto(Optional<Produto> obj) {
        Produto produto = Produto.builder()
                .id(obj.get().getId())
                .descricao(obj.get().getDescricao())
                .tipoCadastro(obj.get().getTipoCadastro())
                .codBarras(obj.get().getCodBarras())
                .nome(obj.get().getNome())
                .preco(obj.get().getPreco())
                .quantidade(obj.get().getQuantidade())
                .build();
        return produto;
    }
}

