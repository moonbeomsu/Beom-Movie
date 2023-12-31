package com.example.beom_movie.service;

import com.example.beom_movie.dto.ReviewDTO;
import com.example.beom_movie.entity.Movie;
import com.example.beom_movie.entity.Review;
import com.example.beom_movie.heart.HeartRepository;
import com.example.beom_movie.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    private final HeartRepository heartRepository;

    @Override
    public List<ReviewDTO> getListOfMovie(Long mno) {

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDto(movieReview)).collect(Collectors.toList());

    }

    @Override
    public Long register(ReviewDTO movieReviewDTO) {

        Review movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getReviewnum();

    }

    @Override
    public void modify(ReviewDTO movieReviewDTO) {

        Optional<Review> result = reviewRepository.findById(movieReviewDTO.getReviewnum());

        if (result.isPresent()) {

            Review movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);
        }

    }


    @Transactional
    @Override
    public void remove(Long reviewnum) {
        //heartRepository 에서 heart 먼저 삭제 필요
        //트랜잭션 처리 필요

        heartRepository.deleteByRno(reviewnum);

        reviewRepository.deleteById(reviewnum);

    }
}
