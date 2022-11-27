package com.Eragoo.todo.task.dto;

import com.Eragoo.todo.task.Task;
import com.Eragoo.todo.user.dto.UserOutputDto;
import lombok.Getter;

import java.time.Instant;

@Getter
public class TaskOutputDto {
    private final Long id;
    private final String name;
    private final String content;
    private final Instant createdAt;
    private final Instant updatedAt;

    public TaskOutputDto(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.content = task.getContent();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
    }
}
