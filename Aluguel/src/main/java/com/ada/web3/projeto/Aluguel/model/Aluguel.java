package com.ada.web3.projeto.Aluguel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
public class Aluguel {

    private String id;
    private Cliente cliente;
    private Filme filme;
    private Boolean alugado;

    public Aluguel(Cliente cliente, Filme filme){
        this.alugado = true;
        this.id = UUID.randomUUID().toString().substring(0,7);
        this.cliente = cliente;
        this.filme = filme;
    }
}
