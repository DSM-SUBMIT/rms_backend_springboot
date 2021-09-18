package com.submit.toyproject.rms_backend_springboot;

import com.submit.toyproject.rms_backend_springboot.domain.field.FieldEnum;
import com.submit.toyproject.rms_backend_springboot.domain.field.FieldRepository;
import com.submit.toyproject.rms_backend_springboot.domain.field.ProjectFieldRepository;
import com.submit.toyproject.rms_backend_springboot.domain.member.MemberRepository;
import com.submit.toyproject.rms_backend_springboot.domain.project.Project;
import com.submit.toyproject.rms_backend_springboot.domain.project.ProjectRepository;
import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private BasicTestSupport basicTestSupport;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProjectFieldRepository projectFieldRepository;

    @BeforeEach
    public void setUp() {
        mvc = basicTestSupport.setUp();

        User user1 = basicTestSupport.createUser("000000@dsm.hs.kr", "홍길동");
        User user2 = basicTestSupport.createUser("111111@dsm.hs.kr", "김길동");
        User user3 = basicTestSupport.createUser("222222@dsm.hs.kr", "곽도현");
        User user4 = basicTestSupport.createUser("333333@dsm.hs.kr", "고금동");
        User user5 = basicTestSupport.createUser("444444@dsm.hs.kr", "김칵수");
        User user6 = basicTestSupport.createUser("555555@dsm.hs.kr", "환승희");

        Project project1 = basicTestSupport.createProject("RMS", user1);
        Project project2 = basicTestSupport.createProject("POW", user2);

        basicTestSupport.addMember(project1, user1, "PM, Server");
        basicTestSupport.addMember(project1, user2, "Design");
        basicTestSupport.addMember(project1, user3, "Design");

        basicTestSupport.addMember(project2, user1, "PM, Server");
        basicTestSupport.addMember(project2, user2, "Design, Client");
        basicTestSupport.addMember(project2, user3, "Design, DevOps");
        basicTestSupport.addMember(project2, user4, "Server");
        basicTestSupport.addMember(project2, user5, "Server");
        basicTestSupport.addMember(project2, user6, "Design, Client");

        basicTestSupport.addProjectField(project1, FieldEnum.APP);
        basicTestSupport.addProjectField(project1, FieldEnum.WEB);

        basicTestSupport.addProjectField(project2, FieldEnum.EMBEDDED);
        basicTestSupport.addProjectField(project2, FieldEnum.AI_BIGDATA);
        basicTestSupport.addProjectField(project2, FieldEnum.APP);
    }

    @AfterEach
    public void deleteAll() {
        basicTestSupport.cleanUp(userRepository);
        basicTestSupport.cleanUp(projectRepository);
        basicTestSupport.cleanUp(memberRepository);
        basicTestSupport.cleanUp(projectFieldRepository);
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200() throws Exception {
        mvc.perform(get("/user/search"))
                .andExpect(status().isOk()).andDo(print());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200_keyword1() throws Exception {
        mvc.perform(get("/user/search?name=김"))
                .andExpect(status().isOk()).andDo(print());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void getUsers_200_keyword2() throws Exception {
        mvc.perform(get("/user/search?name=길동"))
                .andExpect(status().isOk()).andDo(print());
    }

    @WithMockUser
    @Test
    public void getUsers_401() throws Exception {
        mvc.perform(get("/user/search?name=길동"))
                .andExpect(status().isUnauthorized()).andDo(print());
    }

    @WithMockUser(value = "000000@dsm.hs.kr")
    @Test
    public void 마이페이지_보기_200() throws Exception {
        mvc.perform(get("/user/me"))
                .andExpect(status().isOk());
    }

    @WithMockUser
    @Test
    public void 마이페이지가_없다_404() throws Exception {
        mvc.perform(get("/user/me"))
                .andExpect(status().isNotFound());
    }
}
