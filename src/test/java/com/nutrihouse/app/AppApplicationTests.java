package com.nutrihouse.app;

import com.nutrihouse.app.domain.Cliente;
import com.nutrihouse.app.enums.TipoCliente;
import com.nutrihouse.app.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class AppApplicationTests {
	@Autowired
	private ClienteRepository clienteRepository;

	@Test
	void contextLoads() {


	}

}
