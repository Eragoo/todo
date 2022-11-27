package com.Eragoo.todo.task;

import com.Eragoo.todo.error.exception.NotFoundException;
import com.Eragoo.todo.config.security.AuthenticatedUser;
import com.Eragoo.todo.task.dto.TaskInputDto;
import com.Eragoo.todo.task.dto.TaskOutputDto;
import com.Eragoo.todo.user.User;
import com.Eragoo.todo.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<TaskOutputDto> getAll(AuthenticatedUser user) {
        return taskRepository.findAllByCreatedById(user.getId()).stream()
                .map(TaskOutputDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskOutputDto update(AuthenticatedUser user, Long id, TaskInputDto taskInputDto) {
        Task task = taskRepository.findByIdAndCreatedById(id, user.getId())
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found!"));

        task.update(taskInputDto);
        return new TaskOutputDto(task);
    }

    @Transactional
    public TaskOutputDto createTask(AuthenticatedUser user, TaskInputDto taskInputDto) {
        User dbUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("User with id " + user.getId() + " not found!"));

        Task task = new Task(taskInputDto, dbUser);
        taskRepository.save(task);
        return new TaskOutputDto(task);
    }

    @Transactional(readOnly = true)
    public TaskOutputDto getTask(AuthenticatedUser user, Long id) {
        Task task = taskRepository.findByIdAndCreatedById(id, user.getId())
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found!"));

        return new TaskOutputDto(task);
    }

    @Transactional
    public void delete(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id " + id + " not found!"));
        taskRepository.delete(task);
    }
}
