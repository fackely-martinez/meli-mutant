package com.example.melimutant.person.service;

import com.example.melimutant.core.person.command.RatioCommand;
import com.example.melimutant.port.IPersonStatsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class PersonStatsServiceTest {
    @Autowired
    private IPersonStatsService personStatsService;

    @Test
    void test_calculateRatio_when_isOK() throws Exception {
        RatioCommand cmd = RatioCommand.builder()
                .totalMutants(10)
                .totalHumans(3)
                .build();
        double ratio = personStatsService.calculateRatio(cmd);
        Assertions.assertEquals(3.0, ratio);
    }

    @Test
    void test_calculateRatio_when_totalHumansIsZero_isOK() throws Exception {
        RatioCommand cmd = RatioCommand.builder()
                .totalMutants(10)
                .totalHumans(0)
                .build();
        double ratio = personStatsService.calculateRatio(cmd);
        Assertions.assertEquals(0.0, ratio);
    }
}