package com.example.springsession1.member.controller;

import com.example.springsession1.common.consts.Const;
import com.example.springsession1.member.dto.MemberResponseDto;
import com.example.springsession1.member.dto.MemberUpdateRequestDto;
import com.example.springsession1.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @PutMapping("/members")
    public void updateMember(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto dto
    ) {
        memberService.update(memberId,dto);
    }

    @DeleteMapping("/members")
    public void deleteMember(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
    ){
        memberService.deleteById(memberId);
    }
}
