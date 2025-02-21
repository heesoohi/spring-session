package com.example.springsession1.auth.controller;

import com.example.springsession1.auth.dto.AuthSignupRequestDto;
import com.example.springsession1.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto dto){
        authService.signup(dto);
    }
}
