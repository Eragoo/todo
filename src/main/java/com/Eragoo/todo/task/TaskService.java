package com.Eragoo.todo.task;

import com.Eragoo.todo.error.exception.NotFoundException;
import com.Eragoo.todo.task.dto.TaskInputDto;
import com.Eragoo.todo.task.dto.TaskOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public Page<TaskOutputDto> getAll(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(TaskOutputDto::new);
    }

    @Transactional
    public TaskOutputDto update(Long id, TaskInputDto taskInputDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found!"));

        task.update(taskInputDto);
        return new TaskOutputDto(task);
    }

    @Transactional
    public TaskOutputDto createTask(TaskInputDto taskInputDto) {
        Task task = new Task(taskInputDto);
        taskRepository.save(task);
        return new TaskOutputDto(task);
    }

    @Transactional(readOnly = true)
    public TaskOutputDto getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found!"));

        return new TaskOutputDto(task);
    }
}
