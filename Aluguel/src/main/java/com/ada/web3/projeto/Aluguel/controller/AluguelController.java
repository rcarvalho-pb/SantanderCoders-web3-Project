package com.ada.web3.projeto.Aluguel.controller;

import com.ada.web3.projeto.Aluguel.model.Aluguel;
import com.ada.web3.projeto.Aluguel.services.AluguelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "alugueis")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelService service;

    @PostMapping(value = "rent", params = {"clienteId", "filmeId"})
    public Mono<Aluguel> alugar(@RequestParam String clienteID, @RequestParam String filmeID) {
        return Mono.defer(() -> {
            return service.alugar(clienteID, filmeID);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping("search")
    public Flux<Aluguel> findAll() {
        return Flux.defer(service::findAll).subscribeOn(Schedulers.parallel());
    }

    @PostMapping("rent/{id}")
    public Mono<Void> returnAluguel(@PathVariable String id){
        return Mono.defer(() -> {
            return service.returnAluguel(id);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search/{id}")
    public Mono<Aluguel> findById(@PathVariable String id){
        return Mono.defer(() -> {
            return service.findById(id);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search", params = "clienteId")
    public Flux<Aluguel> findByClient(@RequestParam String name){
        return Flux.defer(() -> {
            return service.findByName(name);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search", params = "filmeId")
    public Flux<Aluguel> findByFilme(@RequestParam String filmeId){
        return Flux.defer(() -> {
            return service.findByMovie(filmeId);
        }).subscribeOn(Schedulers.parallel());
    }
}
