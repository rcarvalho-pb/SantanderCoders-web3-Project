package com.ada.web3.projeto.Cliente.controllers;

import com.ada.web3.projeto.Cliente.model.Cliente;
import com.ada.web3.projeto.Cliente.services.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "clientes")
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService service;

    @GetMapping("find/{cpf}")
    public Mono<Cliente> findByCpf(@PathVariable String cpf) {
        return Mono.defer(() -> {
            log.info("controller");
            return service.findById(cpf);
        }).subscribeOn(Schedulers.parallel());
    }

    @PostMapping(value = "save")
    public Mono<Cliente> save(@RequestBody Cliente cliente){
        return Mono.defer(() -> {
            return service.save(cliente);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search", params = "name")
    public Flux<Cliente> findByName(@RequestParam String name) {
        return Flux.defer(() -> {
            return service.findByName(name);
        }).subscribeOn(Schedulers.parallel());
    }

    @GetMapping(value = "search")
    public Flux<Cliente> findAll(){
        return Flux.defer(service::findAll).subscribeOn(Schedulers.parallel());
    }

    @PostMapping(value = "delete/{cpf}")
    public Mono<Void> deleteCliente(@PathVariable String cpf){
        return Mono.defer(() -> {
            return service.delete(cpf);
        }).subscribeOn(Schedulers.parallel());
    }
}
