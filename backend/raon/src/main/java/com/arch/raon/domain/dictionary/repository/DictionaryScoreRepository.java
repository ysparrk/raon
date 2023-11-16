package com.arch.raon.domain.dictionary.repository;

import com.arch.raon.domain.dictionary.entity.DictionaryScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DictionaryScoreRepository extends JpaRepository<DictionaryScore, Long> {
    Optional<DictionaryScore> findByMemberId(Long memberId);
}
