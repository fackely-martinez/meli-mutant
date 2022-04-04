package com.example.melimutant.person.usecase;

import com.example.melimutant.core.person.command.DNACommand;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.core.person.exception.NoMutantException;
import com.example.melimutant.core.person.exception.PersonExistsException;
import com.example.melimutant.port.IPersonRepository;
import com.example.melimutant.port.IPersonService;
import com.example.melimutant.port.IPersonUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class PersonUseCaseTest {

    @Autowired
    private IPersonUseCase personUseCase;

    @MockBean(name = "dynamoRepository")
    private IPersonRepository personRepository;

    @MockBean
    private IPersonService personService;

    @Test
    void test_execute_when_isFull() throws Exception {
        DNACommand command = getCommand();
        PersonDomain domain = getDomain(true);

        when(personRepository.get(domain.getId())).thenReturn(null);
        when(personService.isMutant(command.getDna())).thenReturn(true);
        when(personRepository.save(domain)).thenReturn(domain);

        personUseCase.execute(command);
    }

    @Test
    void test_execute_when_personExists_isError() throws Exception {
        DNACommand command = getCommand();
        PersonDomain domain = getDomain(true);
        String expectedMessage = "PersonUseCase.execute: Person ATGCGA_CAGTGC_TTATGT_AGAAGG_CCCCTA_TCACTG already exists";

        when(personRepository.get(domain.getId())).thenReturn(domain);

        Exception exception = assertThrows(PersonExistsException.class, () -> {
            personUseCase.execute(command);
        });

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void test_execute_when_personIsNotMutant_isError() throws Exception {
        DNACommand command = getCommand();
        PersonDomain domain = getDomain(false);
        String expectedMessage = "PersonUseCase.execute: Person ATGCGA_CAGTGC_TTATGT_AGAAGG_CCCCTA_TCACTG is not a mutant";

        when(personRepository.get(domain.getId())).thenReturn(null);
        when(personService.isMutant(command.getDna())).thenReturn(false);
        when(personRepository.save(domain)).thenReturn(domain);

        Exception exception = assertThrows(NoMutantException.class, () -> {
            personUseCase.execute(command);
        });

        Assertions.assertEquals(expectedMessage, exception.getMessage());
    }

    private DNACommand getCommand() {
        return DNACommand.builder()
                .dna(getDNA())
                .build();
    }

    private PersonDomain getDomain(boolean isMutant) {
        String[] dna = getDNA();
        return PersonDomain.builder()
                .dna(dna)
                .id(String.join("_", dna))
                .isMutant(isMutant)
                .build();
    }

    private String[] getDNA() {
        return new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
    }
}