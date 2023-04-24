package com.ada.web3.projeto.Filme.model;

import com.ada.web3.projeto.Filme.model.dto.FilmeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class ResultSearch {
    @JsonProperty("Search")
    private List<FilmeDTO> resultList;
}