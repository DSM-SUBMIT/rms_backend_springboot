package com.submit.toyproject.rms_backend_springboot;

import com.submit.toyproject.rms_backend_springboot.domain.refreshToken.RefreshToken;
import com.submit.toyproject.rms_backend_springboot.domain.refreshToken.RefreshTokenRepository;
import com.submit.toyproject.rms_backend_springboot.exception.UserNotFoundException;
import com.submit.toyproject.rms_backend_springboot.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RmsBackendSpringbootApplication.class)
@ActiveProfiles("test")
class AuthControllerTest {

    private MockMvc mvc;

    @Autowired
    private BasicTestSupport basicTestSupport;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    final String email = "123456@dsm.hs.kr";
    String refreshToken;

    @BeforeEach
    public void setUp() {
        mvc = basicTestSupport.setUp();

        refreshToken = refreshTokenRepository.save(new RefreshToken(
                        email,
                        jwtTokenProvider.generateRefreshToken(email),
                        100000L
                )
        ).getRefreshToken();
    }

    @AfterEach
    public void deleteAll() {
        basicTestSupport.cleanUp(refreshTokenRepository);
    }

    @Test
    public void 토큰_리프레시_200() throws Exception {

        mvc.perform(put("/auth/token")
                    .header("X-Refresh-Token",
                            refreshTokenRepository.findByRefreshToken(refreshToken)
                                    .orElseThrow(UserNotFoundException::new).getRefreshToken())
                    )
                .andExpect(status().isOk());
    }
}
