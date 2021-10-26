package com.submit.toyproject.rms_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectType;
import com.submit.toyproject.rms_backend_springboot.domain.refreshToken.RefreshTokenRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.dto.request.ProjectRequest;
import com.submit.toyproject.rms_backend_springboot.dto.response.ProjectMemberDto;
import com.submit.toyproject.rms_backend_springboot.security.jwt.JwtTokenProvider;
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

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RmsBackendSpringbootApplication.class)
@ActiveProfiles("test")
class ProjectControllerTest {

    private MockMvc mvc;

    @Autowired
    private BasicTestSupport basicTestSupport;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    final String email = "123456@dsm.hs.kr";

    @BeforeEach
    public void setUp() {
        mvc = basicTestSupport.setUp();

        User user1 = basicTestSupport.createUser("test@dsm.hs.kr", "test");
        User user2 = basicTestSupport.createUser("test2@dsm.hs.kr", "test");
    }

    @AfterEach
    public void deleteAll() {
        basicTestSupport.cleanUp(refreshTokenRepository);
    }

    @WithMockUser(value = "test@dsm.hs.kr")
    @Test
    public void 프로젝트_생성하기_201() throws Exception {
        mvc.perform(post("/project")
                    .content(new ObjectMapper().writeValueAsString(
                            new ProjectRequest("projectName",
                                    "teamName", "techStacks",
                                    ProjectType.CLUB, "teacher",
                                    Arrays.asList(FieldEnum.WEB, FieldEnum.APP),
                                    Arrays.asList(
                                            new ProjectMemberDto(1, "test@dsm.hs.kr", "곽도현", "기술"),
                                            new ProjectMemberDto(2, "test2@dsm.hs.kr", "곽도현", "기술")
                                    )
                            )
                    )).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
    }


    @WithMockUser(value = "test@dsm.hs.kr")
    @Test
    public void 프로젝트_생성하기_멤버에_작성자가_없을때_400() throws Exception {
        mvc.perform(post("/project")
                .content(new ObjectMapper().writeValueAsString(
                        new ProjectRequest("projectName",
                                "teamName", "techStacks",
                                ProjectType.CLUB, "teacher",
                                Arrays.asList(FieldEnum.WEB, FieldEnum.APP),
                                Collections.singletonList(
                                        new ProjectMemberDto(2, "test2@dsm.hs.kr", "곽도현", "기술")
                                )
                        )
                )).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
