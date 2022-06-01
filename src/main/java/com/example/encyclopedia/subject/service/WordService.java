package com.example.encyclopedia.subject.service;

import com.example.encyclopedia.subject.ResponseDto;
import com.example.encyclopedia.subject.domain.Subject;
import com.example.encyclopedia.subject.domain.SubjectRepository;
import com.example.encyclopedia.subject.domain.Word;
import com.example.encyclopedia.subject.domain.WordRepository;
import com.example.encyclopedia.subject.dto.CreateWordRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordService {

    private final SubjectRepository subjectRepository;
    private final WordRepository wordRepository;

    public ResponseDto createWord(CreateWordRequestDto createWordRequestDto) {
        if(subjectRepository.existsByName(createWordRequestDto.getSubjectName())){
            Subject subject = subjectRepository.findByName(createWordRequestDto.getSubjectName());

            Word word = new Word();

            word.setName(createWordRequestDto.getWordName());
            word.setMeaning(createWordRequestDto.getMeaning());
            word.setSubject(subject);

            wordRepository.save(word);

            return new ResponseDto("단어 생성", word.getName());
        }

        Subject subject = new Subject();
        Word word = new Word();

        subject.setName(createWordRequestDto.getSubjectName());
        word.setName(createWordRequestDto.getWordName());
        word.setMeaning(createWordRequestDto.getMeaning());
        word.setSubject(subject);

        subjectRepository.save(subject);
        wordRepository.save(word);

        return new ResponseDto("단어 생성", word.getName());
    }
}
