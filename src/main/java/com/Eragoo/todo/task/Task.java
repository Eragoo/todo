package com.Eragoo.todo.task;


import com.Eragoo.todo.task.dto.TaskInputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Task {
    private static final String GENERATOR = "task_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "task_sequence")
    private Long id;

    private String name;

    private String content;

    private Instant createdAt;
    private int status = 0;

    private Instant updatedAt;

    public Task(TaskInputDto dto) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.createdAt = Instant.now();
        this.status = dto.getStatus();
    }

    public void update(TaskInputDto dto) {
        this.name = dto.getName();
        this.content = dto.getContent();
        this.updatedAt = Instant.now();
        this.status = dto.getStatus();
    }
}
