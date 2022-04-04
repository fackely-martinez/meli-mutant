package com.example.melimutant.adapter.repository.dynamo.mapper;

import com.example.melimutant.adapter.repository.dynamo.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class DynamoMapperTest {

    @Autowired
    private IDynamoMapper repositoryMapper;

    @Test
    void test_modelToDomain_when_isFull() {
        Person model = getPersonModel();

        PersonDomain result = repositoryMapper.modelToDomain(model);

        Assertions.assertEquals(model.getPersonId(), result.getId());
        Assertions.assertEquals(model.getIsMutant(), result.isMutant());
    }

    @Test
    void test_domainToModel_when_isFull() {
        PersonDomain domain = getPersonDomain();

        Person result = repositoryMapper.domainToModel(domain);

        Assertions.assertEquals(domain.getId(), result.getPersonId());
        Assertions.assertEquals(domain.isMutant(), result.getIsMutant());
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
        List<String> lstDna = new ArrayList<>();
        lstDna.add("ATGCGA");
        lstDna.add("CAGTGC");
        lstDna.add("TTATGT");
        lstDna.add("AGAAGG");
        lstDna.add("CCCCTA");
        lstDna.add("TCACTG");
        return Person.builder()
                .dna(lstDna)
                .personId(String.join("_", lstDna))
                .isMutant(Boolean.TRUE)
                .build();
    }
}