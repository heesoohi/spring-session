package com.example.springsession1.member.service;

import com.example.springsession1.member.dto.MemberResponseDto;
import com.example.springsession1.member.dto.MemberUpdateRequestDto;
import com.example.springsession1.member.entity.Member;
import com.example.springsession1.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAllMembers() {
        return memberRepository.findAll().stream()
                .map(member -> new MemberResponseDto(member.getId(), member.getEmail()))
                .toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("not found member with id: " + memberId)
        );
        return new MemberResponseDto(member.getId(), member.getEmail());
    }

    @Transactional
    public void update(Long memberId, MemberUpdateRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("not found member with id: " + memberId)
        );
        member.update(dto.getEmail());
    }

    @Transactional
    public void deleteById(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("not found member with id: " + memberId)
        );
        memberRepository.deleteById(memberId);
    }
}
