package com.submit.toyproject.rms_backend_springboot;

import com.submit.toyproject.rms_backend_springboot.domain.user.User;
import com.submit.toyproject.rms_backend_springboot.domain.user.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        userRepository.save(
                User.builder()
                        .email("202020@gmail.com")
                        .name("홍길동")
                        .githubUrl("https://github.com")
                        .selfIntroduce("안녕안녕")
                        .build()
        );
        userRepository.save(
                User.builder()
                        .email("20202012@gmail.com")
                        .name("김길동")
                        .githubUrl("https://github.com")
                        .selfIntroduce("안녕안녕")
                        .build()
        );
    }

    @AfterEach
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Test
    public void getUsers_200() throws Exception {
        mvc.perform(get("/user/search")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getUsers_200_keyword1() throws Exception {
        mvc.perform(get("/user/search?name=김")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void getUsers_200_keyword2() throws Exception {
        mvc.perform(get("/user/search?name=길동")).andExpect(status().isOk()).andDo(print());
    }

}
