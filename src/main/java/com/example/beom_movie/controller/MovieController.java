package com.example.beom_movie.controller;


import com.example.beom_movie.dto.MovieDTO;
import com.example.beom_movie.dto.PageRequestDTO;
import com.example.beom_movie.entity.Member;
import com.example.beom_movie.repository.MemberRepository;
import com.example.beom_movie.service.MovieService;
import com.example.beom_movie.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    //private final MemberRepository memberRepository;

    @GetMapping("/register")
    public void register(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO,Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
        log.info("movieDTO: " + movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);

        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));

        HttpSession session = request.getSession(false);

        /*if (memberId == null) {
            return "movie/list";
        }*/

        if (session == null) {
            return "movie/list";
        }


        //로그인 처리

        //Optional<Member> loginMember = memberRepository.findById(memberId);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) {
            return "movie/list";
        }
        //Member loginSuccessMember = loginMember.get();
        model.addAttribute("member", loginMember);
        return "login/loginHome";


    }

    @GetMapping("/list3")
    public void list2(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));

    }

    @GetMapping({"/read", "modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model,HttpServletRequest request) {

        log.info("mno: " + mno);

        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto", movieDTO);

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("member", loginMember);

    }

    @PostMapping("/delete/{mno}")
    public String delete(@PathVariable long mno, RedirectAttributes redirectAttributes) {

        log.info("mno :" + mno);

        movieService.deleteWithAll(mno);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";

    }
}
