package com.example.beom_movie.login;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.session.SessionConst;
import com.example.beom_movie.signup.SignupMemberForm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(Model model,HttpServletRequest request) {

        log.info("login - GET 호출 ");
        String redirectURL = request.getParameter("redirectURL");
        log.info("redirectURL = {}", redirectURL);
        request.setAttribute("redirectURL",redirectURL);

        model.addAttribute("loginForm", new LoginForm());
        model.addAttribute("signupMemberForm", new SignupMemberForm());
        return "signup/signup";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult, Model model,
                        @RequestParam(defaultValue = "/movie/list")String redirectURL,HttpServletRequest request) {

        model.addAttribute("signupMemberForm", new SignupMemberForm());

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "/signup/signup";
        }

        log.info("post_redirectURL = {}", redirectURL);



        Member loginMember = loginService.login(form.getEmail(), form.getPassword());
        log.info("login? {}", loginMember);


        if (loginMember == null) {
            //로그인 실패시 오류처리 추가필요

            bindingResult.reject("loginFailError","아이디와 비밀번호를 확인해주세요.");
            log.info("errors={}", bindingResult);
            return "signup/signup";
        }


        String parameter = request.getParameter("redirectURL");
        log.info("parameter = {}", parameter);

        //로그인 성공 처리

        /*Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getMid()));
        response.addCookie(idCookie);*/

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER,loginMember);
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();

        log.info("URI = {}, URL = {}", requestURI, requestURL);
        log.info("URI :{}", redirectURL);

        return "redirect:"+ redirectURL;

    }


    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        /*expireCookie(response, "memberId");*/

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/movie/list";
    }

/*

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
*/

}
