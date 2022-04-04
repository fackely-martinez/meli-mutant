package com.example.melimutant.adapter.api.stats.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Response {
    @JsonProperty("count_mutant_dna")
    private int countMutantDNA;

    @JsonProperty("count_human_dna")
    private int countHumanDNA;

    @JsonProperty("ratio")
    private double ratio;

}
