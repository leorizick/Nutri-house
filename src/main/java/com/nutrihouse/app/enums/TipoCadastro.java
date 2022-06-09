package com.nutrihouse.app.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
public enum TipoCadastro {
    VITAMINA,
    MEDICAMENTO,
    SUPLEMENTO,
    ATIVO,
    DESATIVO;

}


