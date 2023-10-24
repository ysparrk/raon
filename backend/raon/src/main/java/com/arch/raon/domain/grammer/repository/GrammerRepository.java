package com.arch.raon.domain.grammer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arch.raon.domain.grammer.entity.GrammerQuizEntity;

public interface GrammerRepository extends JpaRepository<GrammerQuizEntity, Long> {

}
