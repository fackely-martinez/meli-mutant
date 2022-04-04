package com.example.melimutant.core.person.command;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RatioCommand {
    private int totalMutants;
    private int totalHumans;
}
