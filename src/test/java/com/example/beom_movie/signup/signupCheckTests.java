package com.example.beom_movie.signup;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class signupCheckTests {

    @Autowired
    SignupService signupService;

    @Test
    public void signupDuplicateCheckTests() {

        // true -> 중복 , false -> 중복 x
        boolean checkResult = signupService.checkEmailDuplicate(null);

        log.info("checkResult ={} ", checkResult);


    }


}
