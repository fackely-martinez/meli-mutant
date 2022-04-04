package com.example.melimutant.adapter.repository.local.mapper.impl;

import com.example.melimutant.adapter.repository.local.mapper.IRepositoryMapper;
import com.example.melimutant.adapter.repository.local.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import org.springframework.stereotype.Service;

@Service
public class RepositoryMapper implements IRepositoryMapper {

    @Override
    public Person domainToModel(PersonDomain personDomain) {
        return Person.builder()
                .id(personDomain.getId())
                .isMutant(personDomain.isMutant())
                .dna(personDomain.getDna())
                .build();
    }

    @Override
    public PersonDomain modelToDomain(Person personModel) {
        return PersonDomain.builder()
                .id(personModel.getId())
                .isMutant(personModel.isMutant())
                .dna(personModel.getDna())
                .build();
    }
}
