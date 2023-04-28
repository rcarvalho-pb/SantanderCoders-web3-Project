package com.ada.web3.projeto.Aluguel.services;

import com.ada.web3.projeto.Aluguel.client.ClientCliente;
import com.ada.web3.projeto.Aluguel.client.ClientFilme;
import com.ada.web3.projeto.Aluguel.model.Aluguel;
import com.ada.web3.projeto.Aluguel.repository.AluguelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class AluguelService {

    private final AluguelRepository repository;
    private final ClientFilme cFilme;
    private final ClientCliente cCliente;

    public Mono<Aluguel> alugar(String clienteID, String filmeID){
        return Mono.defer(() -> {
            return Mono.zip(cCliente.findByCpf(clienteID),
                    cFilme.findById(filmeID),
                    Aluguel::new);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Aluguel> findById(String id) {
        return Mono.defer(() -> {
            return Mono.justOrEmpty(repository.findById(id));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Mono<Void> returnAluguel(String id){
           return Mono.fromRunnable(() -> repository.returnAluguel(id))
                   .subscribeOn(Schedulers.boundedElastic()).then();
    }

    public Flux<Aluguel> findAll() {
        return Flux.defer(() -> {
            return Flux.fromIterable(repository.findAll());
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Aluguel> findByName(String clienteID){
        return Flux.defer(() -> {
            return Flux.fromIterable(repository.findByClienteID(clienteID));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Aluguel> findByMovie(String filmeID){
        return Flux.defer(() -> {
            return Flux.fromIterable(repository.findByFilmeID(filmeID));
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
