package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Pedido;
import com.nutrihouse.app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoCrudController {

    @Autowired
    PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> find(@PathVariable Integer id) {
        Pedido pedido = service.find(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pedido);
    }

    @GetMapping
    public ResponseEntity<Page<Pedido>> findPages(@PageableDefault(size = 15, page = 0, sort = "localDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Pedido> pagePedido = service.findPages(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagePedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> save(Pedido pedido) {
        Pedido response = service.save(pedido);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

}
