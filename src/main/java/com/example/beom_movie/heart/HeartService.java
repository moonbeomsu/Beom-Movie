package com.example.beom_movie.heart;

import com.example.beom_movie.entity.Heart;
import com.example.beom_movie.entity.Member;
import com.example.beom_movie.entity.Review;
import com.example.beom_movie.repository.MemberRepository;
import com.example.beom_movie.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void insert(HeartDTO heartDTO) throws Exception {

        Member member = memberRepository.findById(heartDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartDTO.getMemberId()));

        Review review = reviewRepository.findById(heartDTO.getReviewNum())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartDTO.getReviewNum()));

        //이미 좋아요 처리 되어있으면 -> 오류 반환

        if (heartRepository.findByMemberAndReview(member, review).isPresent()) {

            delete(heartDTO);
            return;
            //throw new Exception();
        }

        Heart heart = Heart.builder()
                .review(review)
                .member(member)
                .build();

        heartRepository.save(heart);
        reviewRepository.addLikeCount(review.getReviewnum());

    }

    @Transactional
    public void delete(HeartDTO heartDTO) {

        Member member = memberRepository.findById(heartDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + heartDTO.getMemberId()));

        Review review = reviewRepository.findById(heartDTO.getReviewNum())
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + heartDTO.getReviewNum()));

        Heart heart = heartRepository.findByMemberAndReview(member, review)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));

        heartRepository.delete(heart);

        reviewRepository.subLikeCount(review.getReviewnum());

    }



}
