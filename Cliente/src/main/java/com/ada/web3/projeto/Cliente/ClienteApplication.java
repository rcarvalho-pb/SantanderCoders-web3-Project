package com.ada.web3.projeto.Cliente;

import com.ada.web3.projeto.Cliente.model.Cliente;
import com.ada.web3.projeto.Cliente.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@RequiredArgsConstructor
public class ClienteApplication implements CommandLineRunner {
	private final ClienteRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Cliente c1 = new Cliente("Ramon", "ramon@gmail.com", "12345");
		repository.save(c1);
	}
}
