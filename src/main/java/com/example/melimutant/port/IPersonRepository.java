package com.example.melimutant.port;

import com.example.melimutant.core.person.domain.PersonDomain;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IPersonRepository {
    PersonDomain get(String id) throws JsonProcessingException;

    PersonDomain save(PersonDomain personDomain) throws JsonProcessingException;

    PersonDomain update(PersonDomain personDomain) throws JsonProcessingException;

    List<PersonDomain> getAll();

    Boolean deleteAll();
}
