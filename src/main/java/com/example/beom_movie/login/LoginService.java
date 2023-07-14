package com.example.beom_movie.login;


import com.example.beom_movie.entity.Member;
import com.example.beom_movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;


    public Member login(String email, String password) {

        return memberRepository.findByEmail(email)
                .filter(m -> m.getPw().equals(password))
                .orElse(null);

    }

}
