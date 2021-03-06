package com.nutrihouse.app.domain;


import com.nutrihouse.app.enums.TipoCadastro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private String codBarras;
    private Integer quantidade;
    private Double preco;

    @Enumerated(value = EnumType.STRING)
    private TipoCadastro tipoCadastro;


    public Produto(Integer id, String nome, String descricao, String codBarras, Integer quantidade, Double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.codBarras = codBarras;
        this.quantidade = quantidade;
        this.preco = preco;
        tipoCadastro = TipoCadastro.ATIVO;
    }
}
