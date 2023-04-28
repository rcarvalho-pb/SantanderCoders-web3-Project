package com.ada.web3.projeto.Filme.controllers;

import com.ada.web3.projeto.Filme.client.FilmeOMDBClient;
import com.ada.web3.projeto.Filme.model.Filme;
import com.ada.web3.projeto.Filme.model.ResultSearch;
import com.ada.web3.projeto.Filme.service.FilmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService service;
    @GetMapping("/search/{id}")
    public Mono<Filme> findById(@PathVariable String id){
        return Mono.defer(() -> {
            return service.findById(id);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search", params = "title")
    public Flux<Filme> findByTitle(@RequestParam String title){
        return service.findByTitle(title);
    }
}
