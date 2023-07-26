package com.example.beom_movie.signup;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService{

    private final MemberRepository memberRepository;


    @Override
    public Long register(SignupMemberForm signupMemberForm) {

        Member member = Member.builder()
                .email(signupMemberForm.getEmail())
                .pw(signupMemberForm.getPassword())
                .nickname(signupMemberForm.getNickname())
                .build();

        memberRepository.save(member);
        return member.getMid();
    }

    @Override
    public boolean checkEmailDuplicate(String email) {

        if (email == null || email == "") {
            return false;
        }

        return memberRepository.existsByEmail(email);


    }

    @Override
    public boolean checkNicknameDuplicate(String nickname) {

        if (nickname == null || nickname=="") {
            return false;
        }

        return memberRepository.existsByNickname(nickname);
    }
}
