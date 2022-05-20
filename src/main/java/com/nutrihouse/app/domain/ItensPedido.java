package com.nutrihouse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ItensPedido implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private ItensPedidoPk id = new ItensPedidoPk();

    private Integer quantidade;
    private Double preco;

    public ItensPedido(Pedido pedido, Produto produto, Integer quantidade, Double preco) {
        this.id.setPedido(pedido);
        this.id.setProduto(produto);
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Double getSubTotal(){
        return preco * quantidade;
    }


    @JsonIgnore
    public Pedido getPedido(){
        return id.getPedido();
    }

    @JsonIgnore
    public void setPedido(Pedido pedido){
        id.setPedido(pedido);
    }

    public Produto getProduto(){
        return id.getProduto();
    }

    public void setProduto(Produto produto){
        id.setProduto(produto);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ItensPedido{" +
                "id=" + id.getProduto().getNome() +
                '}';
    }
}
