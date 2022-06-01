package com.nutrihouse.app;

import com.nutrihouse.app.domain.*;
import com.nutrihouse.app.enums.Perfil;
import com.nutrihouse.app.enums.TipoCadastro;
import com.nutrihouse.app.enums.TipoCliente;
import com.nutrihouse.app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private BCryptPasswordEncoder pe;

    @Autowired
    ControleDeEstoque controleDeEstoque;

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
