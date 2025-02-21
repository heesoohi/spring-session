package com.example.springsession1.auth.service;

import com.example.springsession1.auth.dto.AuthSignupRequestDto;
import com.example.springsession1.member.entity.Member;
import com.example.springsession1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public void signup(AuthSignupRequestDto dto) {
        Member member = new Member(dto.getEmail());
        memberRepository.save(member);
    }
}
