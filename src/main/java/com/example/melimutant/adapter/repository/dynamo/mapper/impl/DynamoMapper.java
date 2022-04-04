package com.example.melimutant.adapter.repository.dynamo.mapper.impl;

import com.example.melimutant.adapter.repository.dynamo.mapper.IDynamoMapper;
import com.example.melimutant.adapter.repository.dynamo.model.Person;
import com.example.melimutant.core.person.domain.PersonDomain;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DynamoMapper implements IDynamoMapper {

    /**
     * Method in charge of mapping a model object to a domain
     * @param personModel
     * @return PersonDomain
     */
    @Override
    public PersonDomain modelToDomain(Person personModel) {
        String[] arrayDna = new String[personModel.getDna().size()];
        int index = 0;
        for (String iteratedMessage : personModel.getDna()) {
            arrayDna[index] = iteratedMessage.trim();
            index++;
        }

        return PersonDomain.builder()
                .id(personModel.getPersonId())
                .dna(arrayDna)
                .isMutant(personModel.getIsMutant())
                .build();
    }

    /**
     * Method in charge of mapping a domain object to a model
     * @param personDomain
     * @return Person
     */
    public Person domainToModel(PersonDomain personDomain) {
        List<String> lstDna = new ArrayList<>();

        for (String iterateDna : personDomain.getDna()) {
            lstDna.add(Strings.isBlank(iterateDna) ? " " : iterateDna);
        }

        return Person.builder()
                .personId(personDomain.getId())
                .isMutant(personDomain.isMutant())
                .dna(lstDna)
                .build();
    }
}
