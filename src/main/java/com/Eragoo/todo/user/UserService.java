package com.Eragoo.todo.user;

import com.Eragoo.todo.error.exception.ConflictException;
import com.Eragoo.todo.error.exception.NotFoundException;
import com.Eragoo.todo.user.dto.UserInputDto;
import com.Eragoo.todo.user.dto.UserOutputDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserOutputDto createUser(UserInputDto inputDto) {
        Optional<User> userRepositoryByUsername = userRepository.findByEmail(inputDto.getEmail());

        if (userRepositoryByUsername.isPresent()) {
            throw new ConflictException("User with provided Username already exist!");
        }

        String pass = passwordEncoder.encode(inputDto.getPassword());
        User user = new User(inputDto.getEmail(), pass, inputDto.getUsername());
        userRepository.save(user);
        return new UserOutputDto(user);
    }

    @Transactional(readOnly = true)
    public UserOutputDto getCurrent(String email) {
        return userRepository.findByEmail(email)
                .map(UserOutputDto::new)
                .orElseThrow(() -> new NotFoundException("User with provided username not found!"));
    }
}
