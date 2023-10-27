package com.arch.raon.domain.dictionary.repository;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DictionaryDirectionQuizRepository extends JpaRepository<DictionaryDirectionQuiz, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM dictionary_direction_quiz ORDER BY RAND() LIMIT 3")
    List<DictionaryDirectionQuiz> random3();

}
