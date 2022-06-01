package com.example.encyclopedia.subject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word,Long> {
    boolean existsByNameAndSubject(String WordName, Subject subject);
    /* 이미 생성된 단어가 있을 경우 해당 단어와 동일한 단어를 생성하지 않도록 하기 위한 Method */
}
