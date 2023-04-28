package com.ada.web3.projeto.Aluguel.repository;

import com.ada.web3.projeto.Aluguel.model.Aluguel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AluguelRepository {

    private List<Aluguel> alugueisList = new ArrayList<>();

    public Aluguel alugar(Aluguel aluguel) {
        if(alugueisList.contains(aluguel)) return aluguel;
        alugueisList.add(aluguel);
        return aluguel;
    }

    public Optional<Aluguel> findById(String id) {
        return alugueisList.stream()
                .filter(al -> al.getId().equals(id))
                .findFirst();
    }

    public List<Aluguel> findByClienteID(String clienteID){
        return alugueisList.stream()
                .filter(al -> al.getCliente().getCpf().equals(clienteID))
                .toList();
    }

    public List<Aluguel> findAll() {
        return Collections.unmodifiableList(alugueisList);
    }

    public List<Aluguel> findByFilmeID(String filmeID){
        return alugueisList.stream()
                .filter(al -> al.getFilme().getImdbID().equals(filmeID))
                .toList();
    }

    public void returnAluguel(String id) {
        alugueisList.stream()
                .filter(al -> al.getId().equals(id))
                .findFirst()
                .get()
                .setAlugado(false);
    }

}
