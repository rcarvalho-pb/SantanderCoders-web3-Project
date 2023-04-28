package com.ada.web3.projeto.Aluguel.client;

import com.ada.web3.projeto.Aluguel.model.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class ClientCliente {

    private final WebClient client;
    private final static String url = "cliente";

    public ClientCliente(WebClient.Builder builder){
        this.client = builder.baseUrl(url).build();
    }

    public Mono<Cliente> findByCpf(String cpf){
        return Mono.defer(() -> {
            return client
                    .get()
                    .uri("clientes/search/" + cpf)
                    .retrieve()
                    .bodyToMono(Cliente.class);
        }).subscribeOn(Schedulers.parallel());
    }

    public Flux<Cliente> findByName(String name) {
        return Flux.defer(() -> {
            return client
                    .get()
                    .uri("clientes/search?name=" + name)
                    .retrieve()
                    .bodyToFlux(Cliente.class);
        }).subscribeOn(Schedulers.parallel());
    }


}
