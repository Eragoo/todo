package com.Eragoo.todo.auth.basic;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthInputDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
