package com.example.melimutant.adapter.repository.local.mapper;

import com.example.melimutant.adapter.repository.local.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;

public interface IRepositoryMapper {
    /**
     * Method maps SatelliteDomain from repository to SatelliteModel
     */
    Person domainToModel(PersonDomain personDomain);

    /**
     * Method maps SatelliteModel from repository to SatelliteDomain
     */
    PersonDomain modelToDomain(Person personModel);
}
