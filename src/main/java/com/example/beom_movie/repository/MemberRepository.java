package com.example.beom_movie.repository;

import com.example.beom_movie.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m from Member m where m.email = :email")
    Optional<Member> findByEmail(String email);

}
