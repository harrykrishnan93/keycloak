package com.auth.keycloak.controller;

import com.auth.keycloak.model.*;
import com.auth.keycloak.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(value = "public/login/token")
    public KeycloakTokenResponseDto generateToken(@RequestBody LoginDto loginDto) {
        return authenticationService.generateToken(loginDto);
    }

    @PostMapping(value = "public/user/logout")
    public LogoutResponse logout(@RequestBody KeycloakLogoutDto keycloakLogoutDto) {
        return authenticationService.logout(keycloakLogoutDto);
    }

    @PostMapping(value = "public/session/validate")
    public Object validateToken(@RequestBody KeycloakValidateTokenDto keycloakValidateTokenDto) {
        return authenticationService.validateToken(keycloakValidateTokenDto);
    }


}
