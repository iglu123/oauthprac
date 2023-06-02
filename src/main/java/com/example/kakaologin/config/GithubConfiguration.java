package com.example.kakaologin.config;

import feign.RequestInterceptor;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class GithubConfiguration {
    @Bean
    public OkHttpClient feignGithubClient() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
//            requestTemplate.header("Content-Length", "0");
            requestTemplate.header("Accept", "application/json");
//            requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
//            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));


        };
    }
}
