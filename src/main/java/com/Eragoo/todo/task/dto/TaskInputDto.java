package com.Eragoo.todo.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class TaskInputDto {
    @NotBlank(message = "Task name is required")
    private String name;
    @NotBlank(message = "Task content is required")
    private String content;
    @NotNull(message = "Task status is required")
    private Integer status;
}
