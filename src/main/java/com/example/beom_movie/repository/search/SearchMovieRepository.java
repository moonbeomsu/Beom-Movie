package com.example.beom_movie.repository.search;

import com.example.beom_movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface SearchMovieRepository {

    Movie search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}
