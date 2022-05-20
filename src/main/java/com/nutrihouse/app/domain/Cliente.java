package com.nutrihouse.app.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private TipoCliente tipoCliente;
    private String documento;
    private TipoCadastro tipoCadastro;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido = new ArrayList<>();

    public Cliente(Integer id, String nome, String descricao, TipoCliente tipoCliente, String documento) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.tipoCliente = tipoCliente;
        this.documento = documento;
        tipoCadastro = TipoCadastro.ATIVO;
    }

    @Override
    public String toString() {
        return "" + nome;
    }
}
