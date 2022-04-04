package com.example.melimutant.adapter.api.mutant.mapper.impl;

import com.example.melimutant.adapter.api.mutant.contract.Request;
import com.example.melimutant.adapter.api.mutant.mapper.IMutantMapper;
import com.example.melimutant.core.person.command.DNACommand;
import org.springframework.stereotype.Service;

@Service
public class MutantMapper implements IMutantMapper {
    @Override
    public DNACommand requestToCommand(Request request) {
        return DNACommand.builder()
                .dna(request.getDna())
                .build();
    }
}
