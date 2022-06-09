package com.nutrihouse.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nutrihouse.app.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
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

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    private Set<Integer> perfis = new HashSet<>();

    @Email
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;


    public Usuario(Integer id,String name, String username, String password, Integer perfil) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        addPerfil(Perfil.toEnum(perfil));
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        perfis.add(perfil.getCod());
    }
}
