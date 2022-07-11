package com.Eragoo.todo.task;

import com.Eragoo.todo.task.dto.TaskInputDto;
import com.Eragoo.todo.task.dto.TaskOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor

public class TaskResource {
    private final TaskService taskService;

    @GetMapping
    public Page<TaskOutputDto> getAll(Pageable pageable) {
        return taskService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public TaskOutputDto get(@RequestParam Long id) {
        return taskService.getTask(id);
    }

    @PostMapping("/{id}")
    public TaskOutputDto update(@PathVariable Long id, @RequestBody @Valid TaskInputDto taskInputDto) {
        return taskService.update(id, taskInputDto);
    }

    @PostMapping
    public TaskOutputDto create(@RequestBody @Valid TaskInputDto taskInputDto) {
        return taskService.createTask(taskInputDto);
    }
}
