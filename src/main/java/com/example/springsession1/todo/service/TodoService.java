package com.example.springsession1.todo.service;

import com.example.springsession1.member.entity.Member;
import com.example.springsession1.member.repository.MemberRepository;
import com.example.springsession1.todo.dto.*;
import com.example.springsession1.todo.entity.Todo;
import com.example.springsession1.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<TodoResponseDto> findAll() {
        return todoRepository.findAll().stream()
                .map( todo -> new TodoResponseDto(todo.getId(), todo.getContent()))
                .toList();
    }

    public TodoResponseDto findById(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("Todo not found")
        );

        return new TodoResponseDto(todo.getId(), todo.getContent());
    }

    public TodoUpdateResponseDto updateTodo(Long memberId, Long todoId, TodoUpdateRequestDto dto) {

        Todo todo = getValidatedMemberTodo(memberId, todoId);

        todo.update(dto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    public void deleteById(Long memberId, Long todoId) {

        Todo todo = getValidatedMemberTodo(memberId, todoId);

        todoRepository.deleteById(todoId);
    }

    public Todo getValidatedMemberTodo(Long memberId, Long todoId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalStateException("Member not found")
        );

        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new IllegalStateException("Todo not found")
        );

        if(!todo.getMember().getId().equals(member.getId())){
            throw new IllegalStateException("Invalid member id");
        }

        return todo;
    }
}
