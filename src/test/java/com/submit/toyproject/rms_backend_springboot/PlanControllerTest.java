package com.submit.toyproject.rms_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.PlanRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RmsBackendSpringbootApplication.class)
@ActiveProfiles("test")
public class PlanControllerTest {

    /*private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    Integer id;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        User user = createUser("202020@gmail.com");
        createUser("20202012@gmail.com");
        id = createProject(user).getId();
    }

    @AfterEach
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @WithMockUser(value = "202020@gmail.com")
    @Test
    public void savePlan_201() throws Exception {
        mvc.perform(post("/plan/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createRequest("안녕하십니까")))
        ).andExpect(status().isCreated());
    }

    private User createUser(String email) {
        return User.builder()
                .email(email)
                .name("스프링")
                .build();
    }

    private Project createProject(User user) {
        return Project.builder()
                .projectName("프로젝트")
                .teamName("서브밋")
                .teacher("2학년 담당 선생님")
                .githubUrl("http://github.com")
                .techStacks("Java, Typescript, Springboot, Node.js")
                .serviceUrl("몰랑")
                .docsUrl("http://notion.com")
                .user(user)
                .build();
    }

    private PlanRequest createRequest(String goal) {
        return PlanRequest.builder()
                .goal(goal)
                .content("보고서 관리 시스템")
                .includeResultReport(true)
                .includeCode(true)
                .includeOutCome(true)
                .includeOthers(null)
                .plannedStartDate("2020.09")
                .plannedEndDate("2021.06")
                .build();
    }*/

}
