package com.arch.raon.domain.member.repository;

import com.arch.raon.domain.member.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    List<School> findBySchoolNameContaining(String school);
}
