package com.example.melimutant.adapter.repository.dynamo;

import com.example.melimutant.adapter.repository.dynamo.model.Person;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface IDynamoRepository extends CrudRepository<Person, String> {
}
