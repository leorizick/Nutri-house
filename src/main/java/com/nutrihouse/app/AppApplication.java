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
import java.util.Arrays;

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

        Usuario usuario = new Usuario(null, "simone@gmail.com", pe.encode("1234"));
        usuario.addPerfil(Perfil.ADMIN);
        Usuario usuario2 = new Usuario(null, "leorizick@gmail.com", pe.encode("1234"));
        usuarioRepository.saveAll(Arrays.asList(usuario, usuario2));


        Cliente cli1 = new Cliente(null, "Maria da silva", "Emagrecimento e tratamento diabetes aguda", TipoCliente.PACIENTE, "5001982378");
        Cliente cli2 = new Cliente(null, "Joao de Barro", "Quer ficar grutao", TipoCliente.CLIENTE, "5001982378");
        Cliente cli3 = new Cliente(null, "Ana Maria Braga", "Receitas fitness para emagrecimento", TipoCliente.CLIENTE, "5001982378", TipoCadastro.DESATIVO, null);
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));

        Produto p1 = new Produto(null, "Creme emagrecedor", "Usar 2x ao dia", "790816877283849", 4, 70.00, TipoCadastro.SUPLEMENTO);
        Produto p2 = new Produto(null, "Insulina 200mg", "Aplicar após cada refeição", "798500039293849", 15, 250.00, TipoCadastro.MEDICAMENTO);
        Produto p3 = new Produto(null, "Kit vitaminas b", "Ingerir junto da alimentacao", "798599423848523", 7, 85.00, TipoCadastro.DESATIVO);
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

        Pedido ped1 = new Pedido(null, cli1);
        Pedido ped2 = new Pedido(null, cli2);
        Pedido ped3 = new Pedido(null, cli1);
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));

        ItensPedido ip1 = new ItensPedido(ped1, p1, 4, p1.getPreco());
        ItensPedido ip2 = new ItensPedido(ped1, p2, 2, p2.getPreco());
        ItensPedido ip3 = new ItensPedido(ped2, p3, 1, p3.getPreco());
        ItensPedido ip4 = new ItensPedido(ped3, p3, 1, p3.getPreco());
        ItensPedido ip5 = new ItensPedido(ped3, p1, 1, p1.getPreco());
        ItensPedido ip6 = new ItensPedido(ped3, p2, 1, p2.getPreco());

        ped1.getItensPedidos().addAll(Arrays.asList(ip1, ip2));
        ped2.getItensPedidos().addAll(Arrays.asList(ip3));
        ped3.getItensPedidos().addAll(Arrays.asList(ip4, ip5, ip6));

        itensPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3, ip4, ip5, ip6));

        Receituario r1 = new Receituario(null, "Paciente " + cli1 + ", deve inicar o tratamento para obesidade tomando os remedios a seguir",
                cli1, ped1);
        Receituario r2 = new Receituario(null, "Paciente " + cli2 + ", deve inicar o tratamento para ficar grutao tomando os remedios a seguir",
                cli2, ped2);
        Receituario r3 = new Receituario(null, "Foi definido no retorno do paciente " + cli2 + ", que o mesmo deve encerrar o tratamento dos itens a seguir",
               cli2, ped3);

        receituarioRepository.saveAll(Arrays.asList(r1,r2,r3));

        controleDeEstoque.saidaDeEstoque(ped1);



    }
}
