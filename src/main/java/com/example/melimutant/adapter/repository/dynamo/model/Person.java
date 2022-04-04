package com.example.melimutant.adapter.repository.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamoDBTable(tableName = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = -2403819448413711666L;

    @DynamoDBHashKey(attributeName = "personId")
    private String personId;

    @DynamoDBAttribute
    private Boolean isMutant;

    @DynamoDBAttribute
    private List<String> dna;
}
