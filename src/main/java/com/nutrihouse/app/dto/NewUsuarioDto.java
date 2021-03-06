package com.nutrihouse.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUsuarioDto {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private Integer perfil;
}
