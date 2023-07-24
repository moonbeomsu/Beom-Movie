package com.example.beom_movie.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupMemberForm {

    @Email
    @NotBlank
    private String email;

    @NotNull(message = "null x")
    @NotBlank
    private String password;

    @NotNull(message = "null x")
    @NotBlank
    private String passwordCheck; // password 확인

    @NotNull(message = "null x")
    @NotBlank
    private String nickname;


    public SignupMemberForm() {
    }

    public SignupMemberForm(String email, String password,String passwordCheck, String nickname) {
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.nickname = nickname;

    }
}
