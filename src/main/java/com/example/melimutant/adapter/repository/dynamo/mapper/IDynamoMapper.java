package com.example.melimutant.adapter.repository.dynamo.mapper;

import com.example.melimutant.adapter.repository.dynamo.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;

public interface IDynamoMapper {

    /**
     * Method in charge of mapping a model object to a domain
     *
     * @param personModel
     * @return PersonDomain
     */
    PersonDomain modelToDomain(Person personModel);


    /**
     * Method in charge of mapping a domain object to a model
     *
     * @param personDomain
     * @return Person
     */
    Person domainToModel(PersonDomain personDomain);
}
