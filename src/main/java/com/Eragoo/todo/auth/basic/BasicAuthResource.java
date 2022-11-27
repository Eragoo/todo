package com.Eragoo.todo.auth.basic;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/basic")
@AllArgsConstructor
public class BasicAuthResource {
    private BasicAuthService service;


    @PostMapping("/token")
    public String getJwtTokenByUsernamePassword(@RequestBody UserAuthInputDto command) {
        return service.getToken(command);
    }
}
