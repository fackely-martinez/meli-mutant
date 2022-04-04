package com.example.melimutant.adapter.api.mutant.mapper;

import com.example.melimutant.adapter.api.mutant.contract.Request;
import com.example.melimutant.core.person.command.DNACommand;

public interface IMutantMapper {

    DNACommand requestToCommand(Request request);
}
