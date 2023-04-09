package com.manu.BergfexScraper.controller;

import com.manu.BergfexScraper.service.APIKeyService;
import com.manu.BergfexScraper.service.SkiResortService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(classes = APIController.class)
//@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@ContextConfiguration
@DataJpaTest
//@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@WebMvcTest
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class APIControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SkiResortService skiResortService;
//
    @MockBean
    private APIKeyService apiKeyService;

//    @Autowired
//    private APIKeyService apiKeyService;

    @Test
    void shouldReturnOkStatus() throws Exception {

//        System.out.println(apiKeyService.countKeys());
//        mockMvc.perform(get("/api/v1/resorts").header("api-access-key", "9e27df35-a68e-454b-b063-62a9859f9aff")
//        ).andExpect(status().isOk());
        System.out.println(apiKeyService.countKeys());
    }
}