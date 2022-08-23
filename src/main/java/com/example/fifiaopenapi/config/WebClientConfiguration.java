package com.example.fifiaopenapi.config;

import com.example.fifiaopenapi.api.Apikey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfiguration {
    public static final String BASE_URL = "https://static.api.nexon.co.kr";


    @Bean
    public WebClient webClient() {
        // api key encoding
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);
        // VALUES_ONLY
        return WebClient.builder()
                .uriBuilderFactory(factory)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
