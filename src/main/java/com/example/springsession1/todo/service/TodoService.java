package com.example.springsession1.todo.service;

import com.example.springsession1.member.entity.Member;
import com.example.springsession1.member.repository.MemberRepository;
import com.example.springsession1.todo.dto.TodoSaveRequestDto;
import com.example.springsession1.todo.dto.TodoSaveResponseDto;
import com.example.springsession1.todo.entity.Todo;
import com.example.springsession1.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    public TodoSaveResponseDto saveTodo(Long memberId, TodoSaveRequestDto dto) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("Member not found with id " + memberId)
        );

        Todo todo = new Todo(dto.getContent(), member);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoSaveResponseDto(savedTodo.getId(), member.getId(), savedTodo.getContent());
    }
}
