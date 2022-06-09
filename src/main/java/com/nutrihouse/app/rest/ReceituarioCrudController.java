package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Receituario;
import com.nutrihouse.app.service.ReceituarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/receitas")
public class ReceituarioCrudController implements Serializable {

    @Autowired
    ReceituarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Receituario> find(@PathVariable Integer id) {
        Receituario receituario = service.find(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(receituario);
    }

    @GetMapping
    public ResponseEntity<Page<Receituario>> findPages(@PageableDefault(size = 15, page = 0, sort = "localDateTime", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Receituario> pageReceituario = service.findPages(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageReceituario);
    }

    @PostMapping
    public ResponseEntity<Receituario> save(Receituario receituario) {
        Receituario response = service.save(receituario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
