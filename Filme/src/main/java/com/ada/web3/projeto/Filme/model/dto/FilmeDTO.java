package com.ada.web3.projeto.Filme.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FilmeDTO {
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Title")
    private String title;
}
