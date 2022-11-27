package com.Eragoo.todo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInputDto {
    private final String email;
    private final String password;
    private final String username;
}
