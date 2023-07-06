package com.example.beom_movie.repository;

import com.example.beom_movie.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MovieImageRepository extends JpaRepository<MovieImage,Long> {

    @Modifying
    @Query("delete from MovieImage mi where mi.movie.mno=:mno")
    void deleteByMno(Long mno);


}
