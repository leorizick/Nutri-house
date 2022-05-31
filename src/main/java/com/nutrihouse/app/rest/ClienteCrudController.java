package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Cliente;
import com.nutrihouse.app.domain.Produto;
import com.nutrihouse.app.dto.ClienteDto;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.enums.TipoCliente;
import com.nutrihouse.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteCrudController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        Cliente cliente = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = service.findAll()
                .stream()
                .filter(x -> x.getTipoCadastro() == (TipoCadastro.ATIVO))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/tipo/{tipoCliente}")
    public ResponseEntity<List<Cliente>> findPerAttribute(@PathVariable TipoCliente tipoCliente){
        List<Cliente> list = service.findAll()
                .stream()
                .filter(x -> x.getTipoCliente() == tipoCliente)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ClienteDto clienteDto) {
        ClienteDto response = service.save(clienteDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update (@RequestBody ClienteDto clienteDto, @PathVariable Integer id){
        Cliente cliente =  service.fromDto(clienteDto);
        cliente.setId(id);
       service.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
