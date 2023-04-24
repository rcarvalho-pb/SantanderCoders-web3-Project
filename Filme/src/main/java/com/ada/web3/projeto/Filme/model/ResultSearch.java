package com.ada.web3.projeto.Filme.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ResultSearch {

    @JsonProperty("Search")
    private List<MovieMinimal> resultList;
    private Integer total;
    private Boolean response;
}
se;