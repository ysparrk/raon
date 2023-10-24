package com.arch.raon.domain.dictionary.repository;

import com.arch.raon.domain.dictionary.entity.DictionaryWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryWordRepository extends JpaRepository<DictionaryWord, Long> {
}
