package com.ada.web3.projeto.Filme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filme {
    @JsonProperty("imdbID")
    private String imdbID;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Actors")
    private String actors;

}
