package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import com.example.beom_movie.entity.Movie;
import com.example.beom_movie.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies(){

        IntStream.rangeClosed(1, 100).forEach(i ->{

            Movie movie = Movie.builder().title("Movie..." + i).build();

            System.out.println("---------------------------------------");

            movieRepository.save(movie);

            int count = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg").build();

                imageRepository.save(movieImage);

            }

            System.out.println("==============================================");

        });

    }

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r" + i + "@zerock.org")
                    .pw("1111")
                    .nickname("reviewer" + i).build();
            memberRepository.save(member);
        });

    }

    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()) {
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void testGetMovieWithAll(){

        List<Object[]> result = movieRepository.getMovieWithAll(100L);

        System.out.println(result);

        for (Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }

    }

    @Test
    public void testSearch1() {

        movieRepository.search1();

    }

    @Test
    public void testSearchPage() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending()
                .and(Sort.by("title").ascending()));

        Page<Object[]> result = movieRepository.searchPage("t", "1", pageable);

    }




}
