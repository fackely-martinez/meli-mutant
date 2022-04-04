package com.example.melimutant.core.person.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PersonStatsDomain {
    private int countMutantDNA;
    private int countHumanDNA;
    private double ratio;
}
