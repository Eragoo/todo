package com.Eragoo.todo.config.security;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthenticatedUser extends User {
    private final Long id;
    private final String email;

    public AuthenticatedUser(Long id, String email) {
        super(email, "", Collections.emptyList());
        this.email = email;
        this.id = id;
    }
}
