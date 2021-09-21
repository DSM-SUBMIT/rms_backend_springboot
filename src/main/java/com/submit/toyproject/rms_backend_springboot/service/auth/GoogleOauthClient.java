package com.submit.toyproject.rms_backend_springboot.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.submit.toyproject.rms_backend_springboot.dto.oauth.GoogleOauthResponse;
import com.submit.toyproject.rms_backend_springboot.dto.oauth.GoogleTokenRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class GoogleOauthClient {

    @Value("${oauth.google.token.url}")
    private String GOOGLE_TOKEN_BASE_URL;

    @Value("${oauth.google.tokenInfo.url}")
    private String GOOGLE_TOKEN_INFO_URL;

    public Map<String, String> requestUserInfo(GoogleTokenRequest googleTokenRequest) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(GOOGLE_TOKEN_BASE_URL, googleTokenRequest, String.class);

        ObjectMapper mapper = new ObjectMapper();
            GoogleOauthResponse result = mapper.readValue(responseEntity.getBody()
                    , new TypeReference<>() {});

        String jwtToken = result.getId_token();
        String requestUri = UriComponentsBuilder.fromHttpUrl(GOOGLE_TOKEN_INFO_URL)
                .queryParam("id_token", jwtToken).encode().toUriString();

        String resultJson = restTemplate.getForObject(requestUri, String.class);

        return mapper.readValue(resultJson, new TypeReference<>() {});
    }

}
