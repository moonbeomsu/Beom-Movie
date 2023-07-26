package com.example.beom_movie.signup;

public interface SignupService {

    public Long register(SignupMemberForm signupMemberForm);

    public boolean checkEmailDuplicate(String email);

    public boolean checkNicknameDuplicate(String nickname);
}
