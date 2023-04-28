package com.ada.web3.projeto.Filme.client;

import com.ada.web3.projeto.Filme.model.Filme;
import com.ada.web3.projeto.Filme.model.ResultSearch;
import com.ada.web3.projeto.Filme.model.dto.FilmeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Properties;

@Component
@Slf4j
public class FilmeOMDBClient {

    private final static String baseUrl = "http://www.omdbapi.com/";
    private final WebClient client;

    public FilmeOMDBClient(WebClient.Builder builder){
        this.client = builder.baseUrl(baseUrl).build();
    }

    public Mono<Filme> findById(String id){
        return Mono.defer(() -> {
            return client
                    .get()
                    .uri("?apikey=3b484a54&i="+id)
                    .retrieve()
                    .bodyToMono(Filme.class);
        }).subscribeOn(Schedulers.parallel());
    }

    public Flux<Filme> findByTitle(String title){
        log.info("Comecando Busca");
        return Flux.defer(() -> {
            return client
                    .get()
                    .uri("?apikey=3b484a54&s=" + title)
                    .retrieve()
                    .bodyToMono(ResultSearch.class)
                    .flatMapMany(resultSearch -> Flux.fromIterable(resultSearch.getResultList()))
                    .concatMap(dto -> findById(dto.getImdbID()));
        }).subscribeOn(Schedulers.parallel());

    }
}