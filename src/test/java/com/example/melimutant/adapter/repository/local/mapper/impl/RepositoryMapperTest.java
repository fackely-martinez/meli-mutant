package com.example.melimutant.adapter.repository.local.mapper.impl;

import com.example.melimutant.adapter.repository.local.mapper.IRepositoryMapper;
import com.example.melimutant.adapter.repository.local.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class RepositoryMapperTest {

    @Autowired
    private IRepositoryMapper repositoryMapper;

    @Test
    void test_modelToDomain_when_isFull() {
        Person model = getPersonModel();

        PersonDomain result = repositoryMapper.modelToDomain(model);

        Assertions.assertEquals(model.getId(), result.getId());
        Assertions.assertEquals(model.getDna(), result.getDna());
        Assertions.assertEquals(model.isMutant(), result.isMutant());
    }

    @Test
    void test_domainToModel_when_isFull() {
        PersonDomain domain = getPersonDomain();

        Person result = repositoryMapper.domainToModel(domain);

        Assertions.assertEquals(domain.getId(), result.getId());
        Assertions.assertEquals(domain.getDna(), result.getDna());
        Assertions.assertEquals(domain.isMutant(), result.isMutant());
    }

    private PersonDomain getPersonDomain() {
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
                .isMutant(true)
                .build();
    }

    private Person getPersonModel(){
        String[] dna = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return Person.builder()
                .dna(dna)
                .id(String.join("_", dna))
                .isMutant(true)
                .build();
    }
}