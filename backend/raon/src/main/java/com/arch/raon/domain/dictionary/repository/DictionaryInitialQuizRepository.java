package com.arch.raon.domain.dictionary.repository;

import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictionaryInitialQuizRepository extends JpaRepository<DictionaryInitialQuiz, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM dictionary_initial_quiz ORDER BY RAND() LIMIT 7")
    List<DictionaryInitialQuiz> random7();
}
