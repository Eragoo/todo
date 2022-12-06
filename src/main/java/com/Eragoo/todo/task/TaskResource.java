package com.Eragoo.todo.task;

import com.Eragoo.todo.config.security.AuthenticatedUser;
import com.Eragoo.todo.task.dto.TaskInputDto;
import com.Eragoo.todo.task.dto.TaskOutputDto;
import com.Eragoo.todo.task.dto.TaskPatrykInputDto;
import com.Eragoo.todo.task.dto.TaskPatrykOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor

public class TaskResource {
    private final TaskService taskService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public List<TaskPatrykOutputDto> getAll(@AuthenticationPrincipal AuthenticatedUser user) {
        return taskService.getAll(user).stream()
                .map(t -> new TaskPatrykOutputDto(t, objectMapper))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskPatrykOutputDto get(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return new TaskPatrykOutputDto(taskService.getTask(user, id), objectMapper);
    }

    @PutMapping("/{id}")
    public TaskPatrykOutputDto update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id,
            @RequestBody @Valid TaskPatrykInputDto taskInputDto
    ) {
        return new TaskPatrykOutputDto(taskService.update(user, id, new TaskInputDto(taskInputDto, objectMapper)), objectMapper);
    }

    @PostMapping
    public TaskPatrykOutputDto create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody @Valid TaskPatrykInputDto taskInputDto
    ) {
        return new TaskPatrykOutputDto(taskService.createTask(user, new TaskInputDto(taskInputDto, objectMapper)), objectMapper);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }
}
