package com.ada.web3.projeto.Cliente.repositories;

import com.ada.web3.projeto.Cliente.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component
@Slf4j
public class ClienteRepository {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente save(Cliente cliente) {
        log.info("repository");
        if(findByCpf(cliente.getCpf()).isPresent()) return findByCpf(cliente.getCpf()).get();
        clientes.add(cliente);
        return cliente;
    }

    public List<Cliente> findAll(){
        return Collections.unmodifiableList(clientes);
    }

    public Optional<Cliente> findByCpf(String cpf) {
        return clientes.stream()
                .filter(cliente -> cpf.equals(cliente.getCpf()))
                .findFirst();
    }

    public List<Cliente> findByName(String nome){
        return clientes.stream()
                .filter(cliente -> nome.equalsIgnoreCase(cliente.getNome()))
                .toList();
    }

    public void removeCliente(String cpf){
        if(findByCpf(cpf).isPresent()) {
            clientes.remove(findByCpf(cpf).get());
        }
    }
}
