package com.Eragoo.todo.user;

import com.Eragoo.todo.config.security.AuthenticatedUser;
import com.Eragoo.todo.user.dto.UserInputDto;
import com.Eragoo.todo.user.dto.UserOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/current")
    public UserOutputDto getCurrentUser(@AuthenticationPrincipal AuthenticatedUser user) {
        return userService.getCurrent(user.getEmail());
    }

    @PostMapping
    public UserOutputDto create(@RequestBody UserInputDto inputDto) {
        return userService.createUser(inputDto);
    }
}
