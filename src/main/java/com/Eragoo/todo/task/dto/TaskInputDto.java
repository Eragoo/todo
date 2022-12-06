package com.Eragoo.todo.task.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public TaskInputDto(TaskPatrykInputDto inputDto, ObjectMapper objectMapper) {
        this.name = inputDto.getTitle();
        try {
            this.content = objectMapper.writeValueAsString(inputDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.status = inputDto.getStatus();
    }
}
