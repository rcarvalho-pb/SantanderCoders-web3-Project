package com.ada.web3.projeto.Filme.controllers;

import com.ada.web3.projeto.Filme.client.FilmeOMDBClient;
import com.ada.web3.projeto.Filme.model.Filme;
import com.ada.web3.projeto.Filme.model.ResultList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeOMDBClient client;
    @GetMapping("/search/{id}")
    public Mono<Filme> findById(@PathVariable String id){
        return Mono.defer(() -> {
            return client.findById(id);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "/search", params = "title")
    public Mono<ResultList> findByTitle(@RequestParam String title){

        return Mono.defer(() -> {
            return client.findByTitle(title);
        }).subscribeOn(Schedulers.parallel());
    }
}
