package com.Eragoo.todo.auth.basic;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/basic")
@AllArgsConstructor
public class BasicAuthResource {
    private BasicAuthService service;


    @GetMapping("/token")
    public String getJwtTokenByUsernamePassword(@RequestBody UserAuthInputDto command) {
        return service.getToken(command);
    }
}
