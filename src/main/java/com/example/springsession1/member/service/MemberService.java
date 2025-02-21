package com.example.springsession1.member.service;

import com.example.springsession1.member.dto.MemberResponseDto;
import com.example.springsession1.member.entity.Member;
import com.example.springsession1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getEmail()))
                .toList();
    }

    public MemberResponseDto findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("not found member with id: " + memberId)
        );
        return new MemberResponseDto(member.getId(), member.getEmail());
    }
}
