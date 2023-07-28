package com.example.beom_movie.heart;


import com.example.beom_movie.entity.Review;
import com.example.beom_movie.repository.ReviewRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class heartControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, String> input = new HashMap<>();

    @BeforeEach
    void setBody() {

        Optional<Review> review = reviewRepository.findById(231L);
        if (review.isEmpty()) {
            throw new NotFoundException(" 리뷰 찾을 수 없음");
        }

        input.put("reviewNum", review.get().getReviewnum().toString());
        input.put("memberId", new String("15"));
        log.info("reviewNum , memberId : {} , {}", review.get().getReviewnum().toString(),new String("15"));

    }

    @Test
    public void doHeart() throws Exception{

        mockMvc
                .perform(post("/heart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))

                .andExpect(status().isCreated());

    }


}
