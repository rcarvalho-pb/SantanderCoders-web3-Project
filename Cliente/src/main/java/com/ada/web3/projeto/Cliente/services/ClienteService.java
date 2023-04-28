package com.ada.web3.projeto.Cliente.services;

import com.ada.web3.projeto.Cliente.model.Cliente;
import com.ada.web3.projeto.Cliente.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository repository;

    public Mono<Cliente> save(Cliente cliente){
        return Mono.defer(() -> {
            log.info("service");
            return Mono.just(repository.save(cliente));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Cliente> findById(String id) {
        return Mono.defer(() -> {
            return Mono.justOrEmpty(repository.findByCpf(id));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Cliente> findAll(){
        return Flux.defer(() -> {
            return Flux.fromIterable(repository.findAll());
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Cliente> findByName(String nome){
        return Flux.defer(() -> {
            return Flux.fromIterable(repository.findByName(nome));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> delete(String cpf){
        return Mono.fromRunnable(() -> {
            repository.removeCliente(cpf);
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
}
