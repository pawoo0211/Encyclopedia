package com.example.encyclopedia.subject.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "word")
@NoArgsConstructor
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_Id")
    private Long wordId;

    @Column(name = "name")
    private String name;

    @Column(name = "meaning")
    private String meaning;

    @ManyToOne
    @JoinColumn(name = "id")
    private Subject subject;
}
