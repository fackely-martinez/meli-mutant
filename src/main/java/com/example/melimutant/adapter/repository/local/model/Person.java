package com.example.melimutant.adapter.repository.local.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Person implements Serializable {

    private static final long serialVersionUID = -2403889458414716666L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("is_mutant")
    private boolean isMutant;

    @JsonProperty("dna")
    private String[] dna;
}
