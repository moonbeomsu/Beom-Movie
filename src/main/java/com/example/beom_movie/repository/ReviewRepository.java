package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.entity.Movie;
import com.example.beom_movie.entity.Review;
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

}
