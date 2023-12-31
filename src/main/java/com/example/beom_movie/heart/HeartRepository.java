package com.example.beom_movie.heart;

import com.example.beom_movie.entity.Heart;
import com.example.beom_movie.entity.Member;
import com.example.beom_movie.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    Optional<Heart> findByMemberAndReview(Member member, Review review);

    @Modifying
    @Query("delete from Heart h where h.review.reviewnum=:rno")
    void deleteByRno(Long rno);

}
