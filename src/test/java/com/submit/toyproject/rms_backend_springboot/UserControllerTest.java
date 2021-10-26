package com.submit.toyproject.rms_backend_springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import com.submit.toyproject.rms_backend_springboot.dto.request.StudentNumberRequest;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotFoundException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RmsBackendSpringbootApplication.class)
@ActiveProfiles("test")
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private BasicTestSupport basicTestSupport;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        mvc = basicTestSupport.setUp();

        User user1 = basicTestSupport.createUser("000000@dsm.hs.kr", "홍길동");
        User user2 = basicTestSupport.createUser("111111@dsm.hs.kr", "김길동");
        User user3 = basicTestSupport.createUser("222222@dsm.hs.kr", "고금동");

        Project project1 = basicTestSupport.createProject("RMS", user2);
        Project project2 = basicTestSupport.createProject("POW", user2);
        Project project3 = basicTestSupport.createProject("RMS V2", user2);

        basicTestSupport.addMember(user1, project1, "PM, Server");
        basicTestSupport.addMember(user2, project1, "Design");

        basicTestSupport.addMember(user1, project2, "PM, Server");
        basicTestSupport.addMember(user2, project2, "Design, Client");
        basicTestSupport.addMember(user3, project2, "Design, DevOps");

        basicTestSupport.addMember(user1, project3, "Design, Client");
    }

    @AfterEach
    public void deleteAll() {
        basicTestSupport.cleanUp(userRepository);
        basicTestSupport.cleanUp(projectRepository);
        basicTestSupport.cleanUp(memberRepository);
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200() throws Exception {
        mvc.perform(get("/search"))
                .andExpect(status().isOk()).andDo(print());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200_keyword1() throws Exception {
        mvc.perform(get("/search?name=동"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users.[0].name").value("고금동"))
                .andExpect(jsonPath("$.users.[1].name").value("김길동"))
                .andExpect(jsonPath("$.users.[2].name").value("홍길동"));
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200_keyword2() throws Exception {
        mvc.perform(get("/search?name=길동"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users.[0].name").value("김길동"))
                .andExpect(jsonPath("$.users.[1].name").value("홍길동"));
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void 마이페이지_보기_200() throws Exception {
        mvc.perform(get("/me"))
                .andExpect(status().isOk());

        User user = userRepository.findByEmail("000000@dsm.hs.kr")
                .orElseThrow(UserNotFoundException::new);

        assertEquals(memberRepository.findProjectByUser(user).size(), 3);
    }

    @WithMockUser
    @Test
    public void 마이페이지가_없다_401() throws Exception {
        mvc.perform(get("/me"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void 이름_불러오기_200() throws Exception{
        mvc.perform(get("/name"))
                .andExpect(status().isOk());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void 학번_저장하기_201() throws Exception {
        mvc.perform(patch("/number")
                .content(new ObjectMapper().writeValueAsString(new StudentNumberRequest(2203)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void 학번_저장하기_범위_벗어나서_400() throws Exception {
        mvc.perform(patch("/number")
                .content(new ObjectMapper().writeValueAsString(new StudentNumberRequest(9999)))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

}
