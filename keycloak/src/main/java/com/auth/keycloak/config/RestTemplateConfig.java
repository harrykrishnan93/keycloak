package com.auth.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = getRestTemplate();
        restTemplate.getMessageConverters().add(jacksonSupportsMoreTypes());

        return restTemplate;
    }

    private RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    private HttpMessageConverter jacksonSupportsMoreTypes() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        return converter;
    }
}
