package com.example.kakaologin.client;

import com.example.kakaologin.config.HeaderConfiguration;
import com.example.kakaologin.dto.Facebook.FacebookToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;

@FeignClient(name = "facebookClient",  url="{baseUrl}", configuration = HeaderConfiguration.class)
public interface FacebookClient {
    @GetMapping
    ResponseEntity<String> getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

    @PostMapping
    FacebookToken getToken(URI baseUrl, @RequestParam("client_id") String restApiKey,
                                 @RequestParam("redirect_uri") String redirectUrl,
                                 @RequestParam("client_secret") String secret,
                                 @RequestParam("code") String code,
                                 @RequestParam("grant_type") String grantType);
}
