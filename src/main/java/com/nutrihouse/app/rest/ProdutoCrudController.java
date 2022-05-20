package com.nutrihouse.app.rest;

import com.nutrihouse.app.domain.Produto;
import com.nutrihouse.app.dto.ProdutoDto;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.service.ProdutoService;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoCrudController {

    @Autowired
    ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto produto = service.find(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

//    @GetMapping
//    public ResponseEntity<List<Produto>> findAll(){
//        List<Produto> list = service.findAll();
//        List<Produto> newList = list.stream().filter(x -> x.getTipoCadastro().equals(TipoCadastro.ATIVO)).collect(Collectors.toList());
//        return ResponseEntity.status(HttpStatus.OK).body(newList);
//    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody ProdutoDto produtoDto){
        ProdutoDto response = service.save(produtoDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update (@RequestBody ProdutoDto produtoDto, @PathVariable Integer id){
        Produto produto = service.fromDto(produtoDto);
        produto.setId(id);
        service.update(produto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(value = "/tipo/{tipoCadastro}")
    public ResponseEntity<List<Produto>> findPerAttribute(@PathVariable TipoCadastro tipoCadastro){
        List<Produto> list = service.findAll()
                .stream()
                .filter(x -> x.getTipoCadastro() == tipoCadastro)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }
}
