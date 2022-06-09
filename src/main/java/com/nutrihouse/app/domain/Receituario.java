package com.nutrihouse.app.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Receituario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String descricao;

    @OneToMany
    private Set<ItensPedido> itensPedido;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Receituario(Integer id, String descricao, Cliente cliente, Pedido pedido) {
        this.id = id;
        this.localDateTime = LocalDateTime.now();
        this.descricao = descricao;
        this.cliente = cliente;
        this.pedido = pedido;
        if (this.pedido != null) {
            this.itensPedido = pedido.getItensPedidos();
        }
    }


}
