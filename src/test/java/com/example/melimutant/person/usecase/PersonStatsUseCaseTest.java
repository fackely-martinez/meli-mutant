package com.example.melimutant.person.usecase;

import com.example.melimutant.core.person.command.RatioCommand;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.core.person.domain.PersonStatsDomain;
import com.example.melimutant.core.person.exception.NoContentException;
import com.example.melimutant.port.IPersonRepository;
import com.example.melimutant.port.IPersonStatsService;
import com.example.melimutant.port.IPersonStatsUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PersonStatsUseCaseTest {


    @Autowired
    private IPersonStatsUseCase personStatsUseCase;

    @MockBean(name = "dynamoRepository")
    private IPersonRepository personRepository;

    @MockBean
    private IPersonStatsService personStatsService;

    @Test
    void test_execute_when_isFull() throws Exception {
        RatioCommand command = getCommand();
        PersonStatsDomain domain = getDomain();

        when(personRepository.getAll()).thenReturn(getPersonDomainList());
        when(personStatsService.calculateRatio(command)).thenReturn(0.0);

        PersonStatsDomain result = personStatsUseCase.execute();

        Assertions.assertEquals(domain.getCountHumanDNA(), result.getCountHumanDNA());
        Assertions.assertEquals(domain.getCountMutantDNA(), result.getCountMutantDNA());
        Assertions.assertEquals(domain.getRatio(), result.getRatio());
    }

    @Test
    void test_execute_when_isError() {
        String expectedMessage = "PersonStatsDomain.execute : There aren't people in the repository";

        when(personRepository.getAll()).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(NoContentException.class, () -> {
            personStatsUseCase.execute();
        });

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    private RatioCommand getCommand() {
        return RatioCommand.builder()
                .totalHumans(5)
                .totalMutants(5)
                .build();
    }

    private PersonStatsDomain getDomain() {
        return PersonStatsDomain.builder()
                .countHumanDNA(5)
                .countMutantDNA(5)
                .ratio(0.0)
                .build();
    }

    private PersonDomain getPersonDomain(boolean isMutant) {
        String[] dna = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return PersonDomain.builder()
                .dna(dna)
                .id(String.join("_", dna))
                .isMutant(isMutant)
                .build();
    }

    private List<PersonDomain> getPersonDomainList() {
        List<PersonDomain> personDomainList = new ArrayList<>(0);
        for (int i = 0; i < 5; i++) {
            personDomainList.add(getPersonDomain(true));
            personDomainList.add(getPersonDomain(false));
        }
        return personDomainList;
    }
}