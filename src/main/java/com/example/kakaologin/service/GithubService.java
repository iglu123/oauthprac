package com.example.kakaologin.service;

import com.example.kakaologin.client.GithubClient;
import com.example.kakaologin.client.GoogleClient;
import com.example.kakaologin.dto.Github.GithubToken;
import com.example.kakaologin.dto.Google.GoogleToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class GithubService {
    private final GithubClient client;

    @Value("${github.auth-url}")
    private String githubAuthUrl;

    @Value("${github.client-secret}")
    private String clientSecret;

    @Value("${github.user-api-url}")
    private String githubUserApiUrl;

    @Value("${github.restapi-key}")
    private String restapiKey;

    @Value("${github.redirect-url}")
    private String redirectUrl;

    public ResponseEntity<String> getInfo(final String code) {

        final GithubToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(githubUserApiUrl),token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..", e);
            return null;
        }
    }

    private GithubToken getToken(final String code) {
        try {
            return client.getToken(new URI(githubAuthUrl),restapiKey,clientSecret, code);
        } catch (Exception e) {
            log.error("Something error..", e);
            return GithubToken.fail();
        }
    }
}
