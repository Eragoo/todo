package com.Eragoo.todo.auth.basic;

import com.Eragoo.todo.error.exception.NotFoundException;
import com.Eragoo.todo.config.security.TokenProvider;
import com.Eragoo.todo.user.User;
import com.Eragoo.todo.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class BasicAuthService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TokenProvider tokenProvider;

    public String getToken(UserAuthInputDto command) {
        Optional<User> user = userRepository.findByEmail(command.getEmail());

        if (user.isEmpty() || isPasswordNotMatches(user.get().getPassword(), command.getPassword())) {
            throw new NotFoundException("User with provided username and password not found");
        }

        return tokenProvider.createToken(user.get().getId(), user.get().getEmail());
    }

    private boolean isPasswordNotMatches(String expectedEncodedPassword, String receivedRawPassword) {
        assert expectedEncodedPassword != null && receivedRawPassword != null;
        return !bCryptPasswordEncoder.matches(receivedRawPassword, expectedEncodedPassword);
    }
}
