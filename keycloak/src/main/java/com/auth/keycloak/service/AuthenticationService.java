package com.auth.keycloak.service;

import com.auth.keycloak.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${auth.auth-token-url}")
    private String keycloakTokenUrl;

    @Value("${auth.auth-logout-url}")
    private String keycloakLogoutUrl;

    @Value("${auth.auth-introspect-url}")
    private String keycloakIntrospectUrl;

    @Value("${auth.scope}")
    private String keycloakScope;

    @Value("${auth.grant-type}")
    private String keycloakGrantType;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.resource}")
    private String keycloakResource;

    @Value("${keycloak.credentials.secret}")
    private String keycloakSecret;

    public KeycloakTokenResponseDto generateToken(
            LoginDto keycloakTokenDto) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", keycloakResource);
        map.add("username", keycloakTokenDto.getUsername());
        map.add("password", keycloakTokenDto.getPassword());
        map.add("grant_type", keycloakGrantType);
        map.add("client_secret", keycloakSecret);
        map.add("scope", keycloakScope);

        ResponseEntity<KeycloakTokenResponseDto> responseEntity;
        restTemplate.getMessageConverters().add(new org.springframework.http.converter.FormHttpMessageConverter());
        responseEntity = restTemplate.postForEntity(
                keycloakTokenUrl,
                map,
                KeycloakTokenResponseDto.class);
        return responseEntity.getBody();
    }

    public LogoutResponse logout(
            KeycloakLogoutDto keycloakLogoutDto) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", keycloakResource);
        map.add("client_secret", keycloakSecret);
        map.add("refresh_token", keycloakLogoutDto.getRefreshToken());

        ResponseEntity responseEntity;
        restTemplate.getMessageConverters().add(new org.springframework.http.converter.FormHttpMessageConverter());
        responseEntity = restTemplate.postForEntity(
                keycloakLogoutUrl,
                map,
                Object.class);
        return new LogoutResponse(String.valueOf(responseEntity.getStatusCode().value()), "Logout Successful");
    }

    public Object validateToken(
            KeycloakValidateTokenDto keycloakValidateTokenDto) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", keycloakResource);
        map.add("client_secret", keycloakSecret);
        map.add("token", keycloakValidateTokenDto.getToken());

        ResponseEntity responseEntity;
        restTemplate.getMessageConverters().add(new org.springframework.http.converter.FormHttpMessageConverter());
        responseEntity = restTemplate.postForEntity(
                keycloakIntrospectUrl,
                map,
                Object.class);
        return responseEntity.getBody();
    }
}
