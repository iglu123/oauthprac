package com.example.kakaologin.config;

import feign.Client;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class FeignConfiguration {
    @Bean
    public Client feignClient() {
        return new Client.Default(null, null);
    }
}

