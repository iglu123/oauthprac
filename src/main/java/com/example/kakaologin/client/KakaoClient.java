package com.example.kakaologin.client;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.kakaologin.config.FeignConfiguration;
import com.example.kakaologin.dto.Kakao.KakaoInfo;
import com.example.kakaologin.dto.Kakao.KakaoToken;

@FeignClient(name = "kakaoClient", url="{baseUrl}",configuration = FeignConfiguration.class)
public interface KakaoClient {


    @GetMapping
    KakaoInfo getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    KakaoToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                        @RequestParam("client_secret") String secret,
                        @RequestParam("redirect_uri") String redirectUrl,
                        @RequestParam("code") String code,
                        @RequestParam("grant_type") String grantType);


}
