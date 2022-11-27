package com.Eragoo.todo.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByCreatedById(Long id);
    Optional<Task> findByIdAndCreatedById(Long id, Long userId);
}
