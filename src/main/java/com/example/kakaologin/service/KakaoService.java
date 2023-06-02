package com.example.kakaologin.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kakaologin.client.KakaoClient;
import com.example.kakaologin.dto.Kakao.KakaoInfo;
import com.example.kakaologin.dto.Kakao.KakaoToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final KakaoClient client;

    @Value("${kakao.auth-url}")
    private String kakaoAuthUrl;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    @Value("${kakao.user-api-url}")
    private String kakaoUserApiUrl;

    @Value("${kakao.restapi-key}")
    private String restapiKey;

    @Value("${kakao.redirect-url}")
    private String redirectUrl;

    public KakaoInfo getInfo(final String code) {
        final KakaoToken token = getToken(code);
        log.debug("token = {}", token);
        try {
            return client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("something error..", e);
            return KakaoInfo.fail();
        }
    }

    private KakaoToken getToken(final String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl),restapiKey,clientSecret,  redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("Something error..", e);
            return KakaoToken.fail();
        }
    }
}
