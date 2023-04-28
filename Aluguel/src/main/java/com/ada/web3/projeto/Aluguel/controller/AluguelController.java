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

    @PostMapping(value = "rent/save")
    public Mono<Aluguel> alugar(@RequestBody NewRent rent) {
        return Mono.defer(() -> {
            return service.alugar(rent.clienteID(), rent.filmeID());
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping("search")
    public Flux<Aluguel> findAll() {
        return Flux.defer(service::findAll).subscribeOn(Schedulers.parallel());
    }

    @PostMapping("return-movie/{id}")
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
    public Flux<Aluguel> findByClient(@RequestParam String clienteId){
        return Flux.defer(() -> {
            return service.findByName(clienteId);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search", params = "filmeId")
    public Flux<Aluguel> findByFilme(@RequestParam String filmeId){
        return Flux.defer(() -> {
            return service.findByMovie(filmeId);
        }).subscribeOn(Schedulers.parallel());
    }

    private record NewRent(String clienteID, String filmeID){}
}
