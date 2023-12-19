package com.example.todolist.services;

import com.example.todolist.repository.TaskPagSortRepository;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.entities.TaskEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskPagSortRepository taskPagSortRepository;

    public TaskService(TaskRepository taskRepository, TaskPagSortRepository taskPagSortRepository) {
        this.taskRepository = taskRepository;
        this.taskPagSortRepository = taskPagSortRepository;
    }

    /**
     * Save a task to database
     * @param task object with all fields except id
     * @return the stored task with its id
     */
    public TaskEntity store(TaskEntity task) {

        if (task.getDue_date() != null){
            LocalDateTime newDate = LocalDateTime.from(task.getDue_date());
            task.setDue_date(LocalDateTime.from(task.getDue_date()));
        }


        try {
            return this.taskRepository.save(task);
        }catch (Exception e) {
            throw new RuntimeException("Save error");
        }
    }

    /**
     * Delete a task by its id
     * @param id of a task
     */
    public void delete(Long id) {
        try {
            this.taskRepository.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException("Delete error");
        }
    }

    /**
     * Get a task by its id
     * @param id of a task
     * @return a task with its id
     */
    public TaskEntity getById(Long id) {
        try {
            return this.taskRepository.findById(id).orElse(null);
        }catch (Exception e) {
            throw new RuntimeException("Get error");
        }
    }

    /**
     * Get all tasks with pagination
     * @param page number
     * @param elements number of elements per page
     * @param sortBy column that sort the results
     * @param sortDirection if its descending or ascending
     * @return a page
     */
    public Page<TaskEntity> index(Integer page, Integer elements, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        try {
            return this.taskPagSortRepository.findAll(pageRequest);
        }catch (Exception e) {
            throw new RuntimeException("Index error");
        }
    }
}
