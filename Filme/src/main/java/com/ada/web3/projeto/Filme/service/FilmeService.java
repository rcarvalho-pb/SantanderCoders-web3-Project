package com.ada.web3.projeto.Filme.service;

import com.ada.web3.projeto.Filme.client.FilmeOMDBClient;
import com.ada.web3.projeto.Filme.model.Filme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeOMDBClient client;

    public Mono<Filme> findById(String id) {
        return Mono.defer(() -> {
            return client.findById(id);
        }).subscribeOn(Schedulers.parallel());
    }

    public Flux<Filme> findByTitle(String title) {
        return Flux.defer(() -> {
            return client.findByTitle(title);
        }).subscribeOn(Schedulers.parallel());
    }
}
