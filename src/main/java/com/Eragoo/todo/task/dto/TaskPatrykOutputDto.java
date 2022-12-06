package com.Eragoo.todo.task.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**{
 id: "59h9bdf9n434", // сам генерю ююайді, яĸщо є бажання мож генерити іф емпті, но мож забить
 title: "Task Title",
 desc: "Task Description",
 status: 0, //може бути 0, 1, 2 або -1 наприĸлад яĸщо наїбнулась в біні або
 ще щось, поĸи 0 1 2 яĸщо буде бажання домудри
 dateCreated: "1668380951687.2", //таймстемп
 lastEdit: "1668380951687.2", //таймстемп, не юзаю но передаватиму хз,
 нехай буде
 tags: ["some lable", "work"], // теги, можеш проходитись ловерĸейсом, або
 забий тĸ все одно це сущність тасĸи subtasks: [
 { id: "49bkfdke4", title: "Subtask 1", isChecked: false },
 { id: "49bkfdkfe4", title: "Subtask 2", isChecked: false }, ], // теж сущність тасĸи
 }
 **/
@Getter
public class TaskPatrykOutputDto {
    private final Long id;
    private final String title;
    private final String desc;
    private final int status;
    private final Long dateCreated;
    private final Long lastEdit;
    private final List<String> tags;
    private final List<TaskPatrykInputDto.SubTask> subtasks;

    public TaskPatrykOutputDto(TaskOutputDto outputDto, ObjectMapper objectMapper) {
        TaskPatrykInputDto inputDto = null;
        try {
            inputDto = objectMapper.readValue(outputDto.getContent(), TaskPatrykInputDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        this.id = outputDto.getId();
        this.title = outputDto.getName();
        this.desc = inputDto.getDesc();
        this.status = outputDto.getStatus();
        this.dateCreated = outputDto.getCreatedAt().toEpochMilli();
        this.lastEdit = Optional.ofNullable(outputDto.getUpdatedAt())
                .map(Instant::toEpochMilli)
                .orElse(null);
        this.tags = inputDto.getTags();
        this.subtasks = inputDto.getSubtasks();
    }
}
