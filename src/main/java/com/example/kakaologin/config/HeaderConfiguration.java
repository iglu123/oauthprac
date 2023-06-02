package com.example.kakaologin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Client;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Collections;


public class HeaderConfiguration {

//    public static final String APPLICATION_FORM_URLENCODED_UTF8_VALUE =
//            MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=utf-8";

    @Bean
    public OkHttpClient feignGoogleClient() {
        return new OkHttpClient();
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Length", "0");
            requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
//            requestTemplate.header("Content-Type", "application/x-www-form-urlencoded");
//            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));


        };
    }
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        return converter;
//    }


}
