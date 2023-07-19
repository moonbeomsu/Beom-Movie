package com.example.beom_movie.signup;

import lombok.Data;

@Data
public class SignupMemberForm {


    private String email;

    private String password;

    private String passwordCheck; // password 확인

    private String nickname;



}
