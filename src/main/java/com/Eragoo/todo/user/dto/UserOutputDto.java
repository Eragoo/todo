package com.Eragoo.todo.user.dto;

import com.Eragoo.todo.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserOutputDto {
    private Long id;
    private String email;
    private String username;
    public UserOutputDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
    }
}
