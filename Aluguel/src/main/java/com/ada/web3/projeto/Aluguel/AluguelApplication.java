package com.ada.web3.projeto.Aluguel;

import com.ada.web3.projeto.Aluguel.client.ClientCliente;
import com.ada.web3.projeto.Aluguel.model.Aluguel;
import com.ada.web3.projeto.Aluguel.model.Cliente;
import com.ada.web3.projeto.Aluguel.repository.AluguelRepository;
import com.ada.web3.projeto.Aluguel.services.AluguelService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class AluguelApplication implements CommandLineRunner {

	private final AluguelService service;

	public static void main(String[] args) {
		SpringApplication.run(AluguelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Mono<Aluguel> aluguelMono = service.alugar("12345", "tt0266543");
	}
}
