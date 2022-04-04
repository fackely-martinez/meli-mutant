package com.example.melimutant.core.person.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PersonDomain {
    private String id;
    private boolean isMutant;
    private String[] dna;
}
