package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberSignupTests {

    @Autowired
    private  MemberRepository memberRepository;

    @Test
    public void memberSignup() {

        Member member = Member.builder()
                .email("aaa@abc.com")
                .pw("1111")
                .nickname("abctest").build();

        memberRepository.save(member);

    }


}
