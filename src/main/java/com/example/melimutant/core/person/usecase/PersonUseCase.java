package com.example.melimutant.core.person.usecase;

import com.example.melimutant.core.person.command.DNACommand;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.core.person.exception.NoMutantException;
import com.example.melimutant.core.person.exception.PersonExistsException;
import com.example.melimutant.port.IPersonRepository;
import com.example.melimutant.port.IPersonService;
import com.example.melimutant.port.IPersonUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PersonUseCase implements IPersonUseCase {
    private static final String KEY_DELIMITER = "_";

    private final IPersonService personService;
    private final IPersonRepository personRepository;

    public PersonUseCase(IPersonService personService,
                         @Qualifier("dynamoRepository") IPersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    @Override
    public void execute(DNACommand command) throws Exception {
        String mutantID = String.join(KEY_DELIMITER, command.getDna());
        if (!Objects.isNull(personRepository.get(mutantID))) {
            throw new PersonExistsException("PersonUseCase.execute", mutantID);
        }
        boolean isMutant = personService.isMutant(command.getDna());
        personRepository.save(PersonDomain.builder()
                .id(mutantID)
                .isMutant(isMutant)
                .dna(command.getDna()).build());

        if (!isMutant){
            throw new NoMutantException("PersonUseCase.execute", mutantID);
        }
    }
}
