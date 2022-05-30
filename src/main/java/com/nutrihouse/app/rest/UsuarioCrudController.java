package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Usuario;
import com.nutrihouse.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioCrudController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Integer id){
        Usuario usuario = service.find(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(Usuario usuario){
        Usuario response = service.save(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Integer id){
        Usuario updatedUsuario = service.update(usuario);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
