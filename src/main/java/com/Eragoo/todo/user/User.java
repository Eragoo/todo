package com.Eragoo.todo.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "`user`")
public class User {
    private static final String GENERATOR = "user_generator";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR)
    @SequenceGenerator(name = GENERATOR, sequenceName = "user_sequence")
    private Long id;
    private String email;
    private String password;
    private String username;


    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
