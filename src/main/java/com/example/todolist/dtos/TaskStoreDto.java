package com.example.todolist.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskStoreDto {
    private String title;
    private String description;
    private LocalDateTime due_date;
    private Character status;
}
