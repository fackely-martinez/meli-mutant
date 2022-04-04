package com.example.melimutant.port;

import com.example.melimutant.core.person.command.DNACommand;

public interface IPersonUseCase {
    void execute(DNACommand command) throws Exception;
}
