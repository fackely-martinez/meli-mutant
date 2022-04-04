package com.example.melimutant.adapter.repository.dynamo.impl;

import com.example.melimutant.adapter.repository.dynamo.IDynamoRepository;
import com.example.melimutant.adapter.repository.dynamo.mapper.IDynamoMapper;
import com.example.melimutant.adapter.repository.dynamo.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import com.example.melimutant.port.IPersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Qualifier("dynamoRepository")
public class DynamoRepository implements IPersonRepository {

    private final IDynamoRepository repository;

    private final IDynamoMapper mapper;

    public DynamoRepository(IDynamoRepository repository, IDynamoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Method in charge of saving a Person in dynamoDB
     * @param id object to be registered
     * @return PersonDomain
     */
    @Override
    public PersonDomain get(String id) {
        Optional<Person> person = repository.findById(id);
        if (!person.isPresent()) {
            return null;
        }
        return mapper.modelToDomain(person.get());
    }

    /**
     * Method in charge of saving a Person in dynamoDB
     * @param personDomain object to be registered
     * @return PersonDomain
     * @throws JsonProcessingException
     */
    @Override
    public PersonDomain save(PersonDomain personDomain) throws JsonProcessingException {
        if (existsId(personDomain.getId())) {
            throw new JsonProcessingException(String.format("person %s exists!!", personDomain.getId())) {
            };
        }
        Person person = mapper.domainToModel(personDomain);
        repository.save(person);
        return personDomain;
    }

    /**
     * Method in charge of editing a Person in dynamoDB
     * @param personDomain object to be edits
     * @return PersonDomain
     * @throws JsonProcessingException
     */
    @Override
    public PersonDomain update(PersonDomain personDomain) throws JsonProcessingException {
        if (!existsId(personDomain.getId())) {
            throw new JsonProcessingException(String.format("person %s does not exist!!", personDomain.getId())) {
            };
        }
        Person person = mapper.domainToModel(personDomain);
        return mapper.modelToDomain(repository.save(person));
    }

    /**
     * Method in charge to consult the persons previously saved in dynamoDB
     * @return List<PersonDomain>
     */
    @Override
    public List<PersonDomain> getAll() {
        List<PersonDomain> personsList = new ArrayList<>(0);
        Iterable<Person> listIterator = repository.findAll();
        for (Person personIterator : listIterator) {
            personsList.add(mapper.modelToDomain(personIterator));
        }
        return personsList;
    }

    /**
     * Method in charge of deleting previously saved persons in dynamoDB
     * @return Boolean
     */
    @Override
    public Boolean deleteAll() {
        Iterable<Person> listIterator = repository.findAll();
        while (listIterator.iterator().hasNext()) {
            repository.deleteById(listIterator.iterator().next().getPersonId());
        }
        return Boolean.TRUE;
    }

    /**
     * Method in charge validate by dba if a person already exists in dynamoDB
     * @param id person identifier
     * @return Boolean
     */
    public Boolean existsId(String id){
        return repository.existsById(id);
    }
}
