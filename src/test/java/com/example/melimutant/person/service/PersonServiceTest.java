package com.example.melimutant.person.service;

import com.example.melimutant.port.IPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class PersonServiceTest {

    @Autowired
    private IPersonService personService;

    @Test
    void test_isMutant_when_isTrue() throws Exception {
        String[] dnaMutant = new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"};
        boolean isMutant = personService.isMutant(dnaMutant);
        Assertions.assertTrue(isMutant);
    }

    @Test
    void test_IsMutant_when_isFalse() throws Exception {
        String[] dnaHuman = new String[]{
                "GCGCGC",
                "TATATA",
                "CTCTCT",
                "AGAGAG",
                "TATATA",
                "GCGCGC"};
        boolean isMutant = personService.isMutant(dnaHuman);
        Assertions.assertFalse(isMutant);
    }
}