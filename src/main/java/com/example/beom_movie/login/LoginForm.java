package com.example.beom_movie.login;

import lombok.Data;

@Data
public class LoginForm {

    private String email;

    private String password;

    public LoginForm() {
    }

    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
