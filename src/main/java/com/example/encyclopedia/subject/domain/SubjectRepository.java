package com.example.encyclopedia.subject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    boolean existsByName(String subjectName);
    Subject findByName(String subjectName);
}
