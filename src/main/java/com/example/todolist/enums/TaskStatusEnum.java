package com.example.todolist.enums;

import lombok.Getter;

@Getter
public enum TaskStatusEnum {
    COMPLETE('c'),
    PENDING('p');

    private final Character value;

    TaskStatusEnum(Character value) {
        this.value = value;
    }
}
