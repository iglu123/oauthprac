package com.example.kakaologin.service;


import com.example.kakaologin.client.FacebookClient;
import com.example.kakaologin.dto.Facebook.FacebookToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacebookService {
    private final FacebookClient client;

    @Value("${facebook.auth-url}")
    private String facebookAuthUrl;

    @Value("${facebook.client-secret}")
    private String clientSecret;

    @Value("${facebook.user-api-url}")
    private String facebookUserApiUrl;

    @Value("${facebook.restapi-key}")
    private String restapiKey;

    @Value("${facebook.redirect-url}")
    private String redirectUrl;





    public ResponseEntity<String> getInfo(final String code) {

        final FacebookToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(facebookUserApiUrl),token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..", e);
            return null;
        }
    }

    private FacebookToken getToken(final String code) {
        try {
            return client.getToken(new URI(facebookAuthUrl),restapiKey,clientSecret,  redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);
            return FacebookToken.fail();
        }
    }
}
