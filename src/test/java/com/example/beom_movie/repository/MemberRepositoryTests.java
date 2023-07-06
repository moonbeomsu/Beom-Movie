package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    /*@Autowired
    private ReviewRepository reviewRepository;*/

    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){

        Long mid = 2L;

        Member member = Member.builder().mid(mid).build();


        /*reviewRepository.deleteByMember(member);*/
        memberRepository.deleteById(mid);

    }

}
