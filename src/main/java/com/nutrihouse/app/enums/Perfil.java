package com.nutrihouse.app.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum Perfil {
    ADMIN(1, "ROLE_ADMIN"),
    FUNCIONARIO(2, "ROLE_FUNC");

    private int cod;
    private String descricao;


    private Perfil(int cod, String descricao){
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }


    public String getDescricao() {
        return descricao;
    }

    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Perfil x : Perfil.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id invalido: " + cod);
    }
}
