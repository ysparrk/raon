package com.arch.raon.domain.grammer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arch.raon.domain.grammer.entity.GrammerQuiz;

public interface GrammerRepository extends JpaRepository<GrammerQuiz, Long> {

}
