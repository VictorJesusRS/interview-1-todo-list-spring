package com.example.todolist.controllers;

import com.example.todolist.dtos.TaskStoreDto;
import com.example.todolist.entities.TaskEntity;
import com.example.todolist.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskEntity> store(@RequestBody() TaskEntity task) {
        try {

            return new ResponseEntity<TaskEntity>(this.taskService.store(task), HttpStatus.CREATED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> update(@RequestBody() TaskEntity task) {
        try {
            return ResponseEntity.ok(this.taskService.store(task));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> store(@PathVariable Long id) {
        try {
            this.taskService.delete(id);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.taskService.getById(id));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<Page<TaskEntity>> index(
            @RequestParam( defaultValue = "0") Integer page,
            @RequestParam( defaultValue = "8") Integer elements,
            @RequestParam( defaultValue = "id") String sortBy,
            @RequestParam( defaultValue = "Desc") String sortDirection
    ) {
        try {
            return ResponseEntity.ok(
                    this.taskService.index(page, elements, sortBy, sortDirection)
            );
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
