package com.Eragoo.todo.task.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
@AllArgsConstructor
@Getter
public class TaskPatrykInputDto {
    private String title;
    private String desc;
    private Integer status;
    private List<String> tags = new ArrayList<>();
    private List<SubTask> subtasks = new ArrayList<>();


    @AllArgsConstructor
    @Getter
    public static class SubTask {
        private String id;
        private String title;
        private boolean isChecked;
    }
}
