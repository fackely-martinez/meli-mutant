package com.example.melimutant.adapter.api.stats.mapper.impl;

import com.example.melimutant.adapter.api.stats.contract.Response;
import com.example.melimutant.adapter.api.stats.mapper.IStatsMapper;
import com.example.melimutant.core.person.domain.PersonStatsDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class StatsMapperTest {

    @Autowired
    private IStatsMapper statsMapper;

    @Test
    void test_domainToResponse_when_isFull() {
        PersonStatsDomain domain = getDomain();
        Response result = statsMapper.domainToResponse(domain);

        Assertions.assertEquals(domain.getCountHumanDNA(), result.getCountHumanDNA());
        Assertions.assertEquals(domain.getCountMutantDNA(), result.getCountMutantDNA());
        Assertions.assertEquals(domain.getRatio(), result.getRatio());
    }

    private PersonStatsDomain getDomain() {
        return PersonStatsDomain.builder()
                .countHumanDNA(5)
                .countMutantDNA(5)
                .ratio(0.0)
                .build();
    }
}