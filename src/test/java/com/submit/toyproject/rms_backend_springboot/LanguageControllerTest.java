package com.submit.toyproject.rms_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.domain.language.Language;
import com.submit.toyproject.rms_backend_springboot.domain.language.LanguageRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.LanguageRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RmsBackendSpringbootApplication.class)
@ActiveProfiles("test")
public class LanguageControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private LanguageRepository languageRepository;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        languageRepository.save(new Language("Java"));
        languageRepository.save(new Language("Python"));
    }

    @AfterEach
    public void deleteAll() {
        languageRepository.deleteAll();
    }

    @Test
    public void saveLanguage_201() throws Exception {
        mvc.perform(post("/language")
                .content(new ObjectMapper().writeValueAsString(new LanguageRequest("Javascript")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    public void saveLanguage_409() throws Exception {
        mvc.perform(post("/language")
                .content(new ObjectMapper().writeValueAsString(new LanguageRequest("Java")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }

}
