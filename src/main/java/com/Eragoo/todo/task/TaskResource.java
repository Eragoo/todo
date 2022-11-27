package com.Eragoo.todo.task;

import com.Eragoo.todo.config.security.AuthenticatedUser;
import com.Eragoo.todo.task.dto.TaskInputDto;
import com.Eragoo.todo.task.dto.TaskOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor

public class TaskResource {
    private final TaskService taskService;

    @GetMapping
    public List<TaskOutputDto> getAll(@AuthenticationPrincipal AuthenticatedUser user) {
        return taskService.getAll(user);
    }

    @GetMapping("/{id}")
    public TaskOutputDto get(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser user) {
        return taskService.getTask(user, id);
    }

    @PutMapping("/{id}")
    public TaskOutputDto update(
            @AuthenticationPrincipal AuthenticatedUser user,
            @PathVariable Long id,
            @RequestBody @Valid TaskInputDto taskInputDto
    ) {
        return taskService.update(user, id, taskInputDto);
    }

    @PostMapping
    public TaskOutputDto create(
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody @Valid TaskInputDto taskInputDto
    ) {
        return taskService.createTask(user, taskInputDto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        taskService.delete(id);
    }
}
