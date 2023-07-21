package com.example.beom_movie.signup;


import com.example.beom_movie.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String toSignUpPage(Model model) {
        model.addAttribute("signupMemberForm", new SignupMemberForm());
        model.addAttribute("loginForm", new LoginForm());
        return "/signup/signup";
    }

    @PostMapping("/signup")
    public String signUp(@Validated @ModelAttribute("signupMemberForm") SignupMemberForm signupMemberForm,
                         BindingResult bindingResult,Model model) {


        //예외
        /*
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000,
                        resultPrice}, null);
            } }
        */

        model.addAttribute("loginForm", new LoginForm());

        if (!signupMemberForm.getPassword().equals(signupMemberForm.getPasswordCheck())) {
            bindingResult.reject("passwordCheckError","비밀번호와 비밀번호 확인 값이 다릅니다.");
        }


        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/signup/signup";
        }




        //회원가입 로직
        // signupMemberForm 에서 받아온 데이터로 멤버 만들고 저장 -> JPA 처리 DB 저장
        signupService.register(signupMemberForm);

        log.info("-----------------회원가입 로직------------------");
        log.info("SignupMemberForm ={} ", signupMemberForm);

        return "redirect:/login";
    }
}
