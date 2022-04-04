package com.example.melimutant.adapter.api.mutant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MutantAPITest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostWhenRequestReturnBadRequest() throws Exception {
        String uri = "/mutant";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertEquals(HttpStatus.BAD_REQUEST.value(), mvcResult.getResponse().getStatus());
    }
}