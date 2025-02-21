package com.example.springsession1.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final Long memberId;
    private final String content;

    public TodoSaveResponseDto(Long id, Long memberId, String content){
        this.id = id;
        this.memberId = memberId;
        this.content = content;
    }
}
