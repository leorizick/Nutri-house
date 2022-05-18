package com.nutrihouse.app.dto;

import com.nutrihouse.app.enums.TipoCliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDto {

    private Integer id;
    private String nome;
    private String descricao;
    private TipoCliente tipoCliente;
    private String documento;
}
