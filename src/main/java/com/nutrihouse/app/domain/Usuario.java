package com.nutrihouse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nutrihouse.app.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @Email
    private String username;

    @JsonIgnore
    private String password;
    private String token;

    public Usuario(Integer id, String username, String password, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.token = token;
        addPerfil(Perfil.FUNCIONARIO);
    }

    public Set<Perfil> getPerfis(){
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil){
        perfis.add(perfil.getCod());
    }
}
