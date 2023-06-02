package com.example.kakaologin.service;

import com.example.kakaologin.client.GoogleClient;
import com.example.kakaologin.dto.Google.GoogleToken;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoogleService {
    private final GoogleClient client;

    @Value("${google.auth-url}")
    private String googleAuthUrl;

    @Value("${google.client-secret}")
    private String clientSecret;

    @Value("${google.user-api-url}")
    private String googleUserApiUrl;

    @Value("${google.restapi-key}")
    private String restapiKey;

    @Value("${google.redirect-url}")
    private String redirectUrl;

    @Value("${google.scope}")
    private String scope;



    public ResponseEntity<String> getInfo(final String code) {

        final GoogleToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(googleUserApiUrl),token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..", e);
            return null;
        }
    }

    private GoogleToken getToken(final String code) {
        try {
            return client.getToken(new URI(googleAuthUrl),scope,restapiKey,clientSecret,  redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);
            return GoogleToken.fail();
        }
    }
}
