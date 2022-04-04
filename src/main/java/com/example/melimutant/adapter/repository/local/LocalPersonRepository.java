package com.example.melimutant.adapter.repository.local;

import com.example.melimutant.adapter.repository.local.mapper.IRepositoryMapper;
import com.example.melimutant.adapter.repository.local.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.port.IPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
@Qualifier("localPersonRepository")
public class LocalPersonRepository implements IPersonRepository {

    private final IRepositoryMapper mapper;

    public LocalPersonRepository(IRepositoryMapper mapper) {
        this.mapper = mapper;
    }

    static HashMap<String, Person> personHashMap = new HashMap<>();

    @Override
    public PersonDomain get(String id) throws JsonProcessingException {
        if (Objects.isNull(personHashMap.get(id))) {
            return null;
        }
        return mapper.modelToDomain(personHashMap.get(id));
    }

    @Override
    public PersonDomain save(PersonDomain personDomain) throws JsonProcessingException {
        if (!Objects.isNull(personHashMap.get(personDomain.getId()))) {
            throw new JsonProcessingException(String.format("person %s exists!!", personDomain.getId())) {
            };
        }
        personHashMap.put(personDomain.getId(), mapper.domainToModel(personDomain));
        return mapper.modelToDomain(personHashMap.get(personDomain.getId()));
    }

    @Override
    public PersonDomain update(PersonDomain personDomain) throws JsonProcessingException {
        if (Objects.isNull(personHashMap.get(personDomain.getId()))) {
            throw new JsonProcessingException(String.format("person %s does not exist!!", personDomain.getId())) {
            };
        }
        personHashMap.put(personDomain.getId(), mapper.domainToModel(personDomain));
        return mapper.modelToDomain(personHashMap.get(personDomain.getId()));
    }

    @Override
    public List<PersonDomain> getAll() {
        List<PersonDomain> personDomainList = new ArrayList<>(0);
        for (Person person : personHashMap.values()) {
            personDomainList.add(mapper.modelToDomain(person));
        }
        return personDomainList;
    }

    @Override
    public Boolean deleteAll() {
        personHashMap = new HashMap<>();
        return Boolean.TRUE;
    }

}
