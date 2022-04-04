package com.example.melimutant.adapter.repository;

import com.example.melimutant.adapter.repository.local.mapper.IRepositoryMapper;
import com.example.melimutant.adapter.repository.local.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.port.IPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class LocalPersonRepositoryTest {

    @MockBean
    private IRepositoryMapper repositoryMapper;

    @Autowired
    @Qualifier("localPersonRepository")
    private IPersonRepository personRepository;

    @Test
    void test_get_when_isDomain() throws JsonProcessingException {
        PersonDomain domain = getPersonDomain(true);
        Person model = getPersonModel(true);

        when(repositoryMapper.domainToModel(domain)).thenReturn(model);
        when(repositoryMapper.modelToDomain(model)).thenReturn(domain);

        personRepository.deleteAll();
        personRepository.save(domain);
        PersonDomain result = personRepository.get(domain.getId());

        Assertions.assertEquals(domain.getId(), result.getId());
        Assertions.assertEquals(domain.getDna(), result.getDna());
        Assertions.assertEquals(domain.isMutant(), result.isMutant());
    }

    @Test
    void test_get_when_isError() throws JsonProcessingException {
        PersonDomain domain = getPersonDomain(true);

        personRepository.deleteAll();
        PersonDomain result = personRepository.get(domain.getId());

        Assertions.assertNull(result);
    }

    @Test
    void test_save_when_isDomain() throws JsonProcessingException {
        PersonDomain domain = getPersonDomain(true);
        Person model = getPersonModel(true);

        when(repositoryMapper.domainToModel(domain)).thenReturn(model);
        when(repositoryMapper.modelToDomain(model)).thenReturn(domain);

        personRepository.deleteAll();
        PersonDomain result = personRepository.save(domain);

        Assertions.assertEquals(domain.getId(), result.getId());
        Assertions.assertEquals(domain.getDna(), result.getDna());
        Assertions.assertEquals(domain.isMutant(), result.isMutant());
    }

    @Test
    void test_save_when_isError() throws JsonProcessingException {
        PersonDomain domain = getPersonDomain(true);
        Person model = getPersonModel(true);
        String expectedMessage = "person ATGCGA_CAGTGC_TTATGT_AGAAGG_CCCCTA_TCACTG exists!!";

        when(repositoryMapper.domainToModel(domain)).thenReturn(model);
        when(repositoryMapper.modelToDomain(model)).thenReturn(domain);

        personRepository.deleteAll();
        personRepository.save(domain);
        Exception exception = assertThrows(JsonProcessingException.class, () -> {
            personRepository.save(domain);
        });

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void test_update_when_isDomain() throws JsonProcessingException {
        PersonDomain domain = getPersonDomain(true);
        Person model = getPersonModel(true);

        when(repositoryMapper.domainToModel(domain)).thenReturn(model);
        when(repositoryMapper.modelToDomain(model)).thenReturn(domain);

        personRepository.deleteAll();
        personRepository.save(domain);
        PersonDomain result = personRepository.update(domain);

        Assertions.assertEquals(domain.getId(), result.getId());
        Assertions.assertEquals(domain.getDna(), result.getDna());
        Assertions.assertEquals(domain.isMutant(), result.isMutant());
    }

    @Test
    void test_update_when_isError(){
        PersonDomain domain = getPersonDomain(true);
        String expectedMessage = "person ATGCGA_CAGTGC_TTATGT_AGAAGG_CCCCTA_TCACTG does not exist!!";

        personRepository.deleteAll();
        Exception exception = assertThrows(JsonProcessingException.class, () -> {
            personRepository.update(domain);
        });

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    void test_getAll_when_isDomain() throws JsonProcessingException {
        PersonDomain mutantDomain = getPersonDomain(true);
        Person mutantModel = getPersonModel(true);
        PersonDomain humanDomain = getPersonDomain(false);
        Person humanModel = getPersonModel(false);

        when(repositoryMapper.domainToModel(mutantDomain)).thenReturn(mutantModel);
        when(repositoryMapper.modelToDomain(mutantModel)).thenReturn(mutantDomain);

        when(repositoryMapper.domainToModel(humanDomain)).thenReturn(humanModel);
        when(repositoryMapper.modelToDomain(humanModel)).thenReturn(humanDomain);

        personRepository.deleteAll();
        personRepository.save(mutantDomain);
        personRepository.save(humanDomain);
        List<PersonDomain> result = personRepository.getAll();

        Assertions.assertEquals(result.size(), 2);
    }

    private String[] getMutantDNA() {
        return new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
    }

    private String[] getHumanDNA() {
        return new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTACGT",
                "AGAAGG",
                "CCCATA",
                "TCACTG"};
    }

    private PersonDomain getPersonDomain(boolean isMutant) {
        String[] dna = isMutant ? getMutantDNA() : getHumanDNA();
        return PersonDomain.builder()
                .dna(dna)
                .id(String.join("_", dna))
                .isMutant(isMutant)
                .build();
    }

    private List<PersonDomain> getPersonDomainList() {
        List<PersonDomain> personDomainList = new ArrayList<>(0);
        personDomainList.add(getPersonDomain(true));
        personDomainList.add(getPersonDomain(false));
        return personDomainList;
    }

    private Person getPersonModel(boolean isMutant) {
        String[] dna =isMutant ? getMutantDNA() : getHumanDNA();
        return Person.builder()
                .dna(dna)
                .id(String.join("_", dna))
                .isMutant(isMutant)
                .build();
    }

    private List<Person> getPersonModelList() {
        List<Person> personList = new ArrayList<>(0);
        personList.add(getPersonModel(true));
        personList.add(getPersonModel(false));
        return personList;
    }
}