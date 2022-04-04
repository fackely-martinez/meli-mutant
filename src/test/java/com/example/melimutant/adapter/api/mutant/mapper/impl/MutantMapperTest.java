package com.example.melimutant.adapter.api.mutant.mapper.impl;

import com.example.melimutant.adapter.api.mutant.contract.Request;
import com.example.melimutant.adapter.api.mutant.mapper.IMutantMapper;
import com.example.melimutant.core.person.command.DNACommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class MutantMapperTest {

    @Autowired
    private IMutantMapper mutantMapper;

    @Test
    void test_requestToCommand_when_isFull() {
        Request request = getRequest();
        DNACommand result = mutantMapper.requestToCommand(request);

        Assertions.assertEquals(request.getDna(), result.getDna());
    }

    private Request getRequest() {
        String[] dna = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        return Request.builder()
                .dna(dna)
                .build();
    }
}