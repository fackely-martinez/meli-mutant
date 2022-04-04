package com.example.melimutant.core.person.usecase;

import com.example.melimutant.core.person.command.RatioCommand;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.core.person.domain.PersonStatsDomain;
import com.example.melimutant.core.person.exception.NoContentException;
import com.example.melimutant.port.IPersonRepository;
import com.example.melimutant.port.IPersonStatsService;
import com.example.melimutant.port.IPersonStatsUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonStatsUseCase implements IPersonStatsUseCase {
    private final IPersonRepository personRepository;
    private final IPersonStatsService personStatsService;

    public PersonStatsUseCase(@Qualifier("dynamoRepository") IPersonRepository personRepository,
                              IPersonStatsService personStatsService) {
        this.personRepository = personRepository;
        this.personStatsService = personStatsService;
    }

    @Override
    public PersonStatsDomain execute() {
        List<PersonDomain> personDomainList = personRepository.getAll();
        if (personDomainList.isEmpty()) {
            throw new NoContentException("PersonStatsDomain.execute");
        }

        RatioCommand ratioCommand = RatioCommand.builder()
                .totalHumans((int) personDomainList.stream().filter(p -> !p.isMutant()).count())
                .totalMutants((int) personDomainList.stream().filter(PersonDomain::isMutant).count())
                .build();

        return PersonStatsDomain.builder()
                .countHumanDNA(ratioCommand.getTotalHumans())
                .countMutantDNA(ratioCommand.getTotalMutants())
                .ratio(personStatsService.calculateRatio(ratioCommand))
                .build();
    }
}
