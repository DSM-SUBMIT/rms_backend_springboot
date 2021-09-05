package com.submit.toyproject.rms_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.domain.team.Team;
import com.submit.toyproject.rms_backend_springboot.domain.team.TeamRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.TeamRequest;
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
public class TeamControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        teamRepository.save(new Team("submit"));
    }

    @AfterEach
    public void deleteAll() {
        teamRepository.deleteAll();
    }

    @Test
    public void saveTeam_201() throws Exception {
        mvc.perform(post("/team")
                .content(new ObjectMapper().writeValueAsString(new TeamRequest("gram")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    public void saveTeam_409() throws Exception {
        mvc.perform(post("/team")
                .content(new ObjectMapper().writeValueAsString(new TeamRequest("submit")))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isConflict());
    }

}
