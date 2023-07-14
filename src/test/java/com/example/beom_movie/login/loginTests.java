package com.example.beom_movie.login;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@Slf4j
@SpringBootTest
public class loginTests {

    @Autowired
    private  MemberRepository memberRepository;
    @Autowired
    private  LoginService loginService;


    @Test
    public void findByEmailTest() {

        Optional<Member> result = memberRepository.findByEmail("r15@zerock.org");

        Member member = result.get();

        log.info("member = {}",member);

    }

    @Test
    public void loginTest() {

        //성공
        Member loginMember = loginService.login("r15@zerock.org", "1111");

        log.info("loginMember ={}", loginMember);


        // 실패(비밀번호 틀림)
        Member loginMember_fail = loginService.login("r15@zerock.org", "2222");

        log.info("loginMember_fail ={}", loginMember_fail);

    }

}
