package com.example.kakaologin.client;

import com.example.kakaologin.config.FeignConfiguration;
import com.example.kakaologin.config.HeaderConfiguration;
import com.example.kakaologin.dto.Google.GoogleToken;
import com.example.kakaologin.dto.Naver.NaverToken;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "googleClient", url="{baseUrl}", configuration = {HeaderConfiguration.class})
public interface GoogleClient {


    @GetMapping
    ResponseEntity<String> getInfo(URI baseUrl,
//                                   @RequestHeader("Content-Length") String contentLength,
                                   @RequestHeader("Authorization") String accessToken);


    @PostMapping
    GoogleToken getToken(URI baseUrl,
//                         @RequestHeader("Content-Length") String contentLength,
                         @RequestParam("scope") String scope,
                         @RequestParam("client_id") String restApiKey,
                         @RequestParam("client_secret") String secret,
                         @RequestParam("redirect_uri") String redirectUrl,
                         @RequestParam("code") String code,
                         @RequestParam("grant_type") String grantType);

}
