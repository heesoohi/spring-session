package com.example.springsession1.todo.controller;

import com.example.springsession1.common.consts.Const;
import com.example.springsession1.todo.dto.TodoSaveRequestDto;
import com.example.springsession1.todo.dto.TodoSaveResponseDto;
import com.example.springsession1.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> saveTodo(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody TodoSaveRequestDto dto
    ){
        return ResponseEntity.ok(todoService.saveTodo(memberId, dto));
    }
}
