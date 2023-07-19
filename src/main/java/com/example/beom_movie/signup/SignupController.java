package com.example.beom_movie.signup;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;


    @GetMapping("/signup")
    public String toSignUpPage() {

        return "/signup/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signupMemberForm") SignupMemberForm signupMemberForm, RedirectAttributes redirectAttributes) {

        //회원가입 로직
        // signupMemberForm 에서 받아온 데이터로 멤버 만들고 저장 -> JPA 처리 DB 저장
        signupService.register(signupMemberForm);

        log.info("-----------------------------------");
        log.info("SignupMemberForm ={} ", signupMemberForm);

        return "redirect:/login";
    }
}
