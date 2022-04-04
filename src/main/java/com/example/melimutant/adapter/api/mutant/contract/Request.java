package com.example.melimutant.adapter.api.mutant.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Request {
    @JsonProperty(value = "dna", required = true)
    private String[] dna;

    public boolean isValid() {
        return dna.length > 5;
    }
}
