package com.nutrihouse.app;

import com.nutrihouse.app.domain.ControleDeEstoque;
import com.nutrihouse.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {


    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    ItensPedidoRepository itensPedidoRepository;
    @Autowired
    ReceituarioRepository receituarioRepository;
    @Autowired
    ControleDeEstoque controleDeEstoque;
    @Autowired
    private BCryptPasswordEncoder pe;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
