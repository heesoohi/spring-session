package com.example.springsession1.todo.service;

import com.example.springsession1.member.repository.MemberRepository;
import com.example.springsession1.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;


}
