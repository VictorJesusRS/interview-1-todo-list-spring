package com.example.todolist.repository;

import com.example.todolist.entities.TaskEntity;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskPagSortRepository extends ListPagingAndSortingRepository<TaskEntity, Long> {
}
