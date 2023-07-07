package com.example.beom_movie.controller;


import com.example.beom_movie.dto.MovieDTO;
import com.example.beom_movie.dto.PageRequestDTO;
import com.example.beom_movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO: " + movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));

    }

    @GetMapping("/list3")
    public void list2(PageRequestDTO pageRequestDTO, Model model) {

        log.info("pageRequestDTO : " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));

    }

    @GetMapping({"/read", "modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("mno: " + mno);

        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto", movieDTO);

    }

    @PostMapping("/delete/{mno}")
    public String delete(@PathVariable long mno, RedirectAttributes redirectAttributes) {

        log.info("mno :" + mno);

        movieService.deleteWithAll(mno);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";

    }
}
