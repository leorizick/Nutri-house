package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Usuario;
import com.nutrihouse.app.dto.NewUsuarioDto;
import com.nutrihouse.app.service.UsuarioService;
import com.nutrihouse.app.service.exceptions.SQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioCrudController {

    @Autowired
    private UsuarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario usuario = service.find(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuario);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> list = service.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping(value = "/username")
    public ResponseEntity<Usuario> find(@RequestParam (value = "value") String username){
        Usuario usuario = service.findByUsername(username);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody NewUsuarioDto usuario) {

        Usuario newUsuario = service.fromDto(usuario);
        Usuario response = service.save(newUsuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Usuario> update(@RequestBody NewUsuarioDto newUsuarioDto, @PathVariable Integer id) {
        Usuario usuario = service.fromDto(newUsuarioDto);
        usuario.setId(id);
        service.update(usuario);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
