package com.example.kakaologin.client;

import com.example.kakaologin.config.FeignConfiguration;
import com.example.kakaologin.config.GithubConfiguration;
import com.example.kakaologin.config.HeaderConfiguration;
import com.example.kakaologin.dto.Github.GithubToken;
import com.example.kakaologin.dto.Naver.NaverToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "githubClient", configuration = GithubConfiguration.class)
public interface GithubClient {
    @GetMapping
    ResponseEntity<String> getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    GithubToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
//                         @RequestParam("redirect_uri") String redirectUrl,
                         @RequestParam("client_secret") String secret,
                         @RequestParam("code") String code
//                         @RequestParam("grant_type") String grantType
    );
}
