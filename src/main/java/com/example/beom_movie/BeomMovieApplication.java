package com.example.beom_movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BeomMovieApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeomMovieApplication.class, args);
    }

}
