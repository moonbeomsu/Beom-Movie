package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.entity.Movie;
import com.example.beom_movie.entity.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);


    @Modifying
    @Query("delete from Review mr where mr.member = :member")
    void deleteByMember(Member member);

    @Modifying
    @Query("delete from Review r where r.movie.mno =:mno")
    void deleteByMno(Long mno);

    @Modifying
    @Query("UPDATE Review r set r.likeCount = r.likeCount +1 where r.reviewnum = :reviewNum" )
     void addLikeCount(Long reviewNum);

    @Modifying
    @Query("UPDATE Review r set r.likeCount = r.likeCount -1 where r.reviewnum = :reviewNum" )
    void subLikeCount(Long reviewNum);



}
