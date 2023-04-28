package com.ada.web3.projeto.Aluguel.client;

import com.ada.web3.projeto.Aluguel.model.Filme;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
public class ClientFilme {

    private final WebClient client;
    private static String url = "/filme";

    public ClientFilme(WebClient.Builder builder){
        this.client = builder.baseUrl(url).build();
    }

    public Mono<Filme> findById(String id) {
        return Mono.defer(() -> {
            return client
                    .get()
                    .uri("filmes/search/" + id)
                    .retrieve()
                    .bodyToMono(Filme.class);
        }).subscribeOn(Schedulers.parallel());

    }

    public Flux<Filme> findByName(String name) {
        return Flux.defer(() -> {
            return client
                    .get()
                    .uri("filmes/search?name=" + name)
                    .retrieve()
                    .bodyToFlux(Filme.class);
        }).subscribeOn(Schedulers.parallel());
    }

}
