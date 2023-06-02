package com.example.kakaologin.service;

import java.net.URI;

import com.example.kakaologin.client.NaverClient;
import com.example.kakaologin.dto.Naver.NaverInfo;
import com.example.kakaologin.dto.Naver.NaverToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.kakaologin.client.KakaoClient;
import com.example.kakaologin.dto.Kakao.KakaoInfo;
import com.example.kakaologin.dto.Kakao.KakaoToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverService {

    private final NaverClient client;

    @Value("${naver.auth-url}")
    private String naverAuthUrl;

    @Value("${naver.client-secret}")
    private String clientSecret;

    @Value("${naver.user-api-url}")
    private String naverUserApiUrl;

    @Value("${naver.restapi-key}")
    private String restapiKey;

    @Value("${naver.redirect-url}")
    private String redirectUrl;

    public ResponseEntity<String> getInfo(final String code) {

        final NaverToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(naverUserApiUrl),token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..", e);
            return null;
        }
    }

    private NaverToken getToken(final String code) {
        try {

            return client.getToken(new URI(naverAuthUrl),restapiKey,clientSecret,  redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);
            return NaverToken.fail();
        }
    }


}
