package com.ada.web3.projeto.Filme.client;

import com.ada.web3.projeto.Filme.model.Filme;
import com.ada.web3.projeto.Filme.model.ResultSearch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
        return client
                .get()
                .uri("?apikey=3b484a54&i="+id)
                .retrieve()
                .bodyToMono(Filme.class);
    }

    public Mono<ResultSearch> findByTitle(String title){
        log.info("Comecando Busca");
        return  client
                .get()
                .uri("?apikey=3b484a54&s=" + title)
                .retrieve()
                .bodyToMono(ResultSearch.class);
    }
}