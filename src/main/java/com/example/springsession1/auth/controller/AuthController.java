package com.example.springsession1.auth.controller;

import com.example.springsession1.auth.dto.AuthLoginRequestDto;
import com.example.springsession1.auth.dto.AuthLoginResponseDto;
import com.example.springsession1.auth.dto.AuthSignupRequestDto;
import com.example.springsession1.auth.service.AuthService;
import com.example.springsession1.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequestDto dto, HttpServletRequest request){
        AuthLoginResponseDto result = authService.login(dto);
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_MEMBER, result.getMemberId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }
}
