package com.example.encyclopedia.subject.service;

import com.example.encyclopedia.subject.ResponseDto;
import com.example.encyclopedia.subject.domain.Subject;
import com.example.encyclopedia.subject.domain.SubjectRepository;
import com.example.encyclopedia.subject.domain.Word;
import com.example.encyclopedia.subject.domain.WordRepository;
import com.example.encyclopedia.subject.dto.CreateWordRequestDto;
import com.example.encyclopedia.subject.dto.GetWordListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@RequiredArgsConstructor
public class WordService {

    private final SubjectRepository subjectRepository;
    private final WordRepository wordRepository;

    public ResponseDto createWord(CreateWordRequestDto createWordRequestDto) {
        if (subjectRepository.existsByName(createWordRequestDto.getSubjectName())) {
            Subject subject = subjectRepository.findByName(createWordRequestDto.getSubjectName());

            if(wordRepository.existsByNameAndSubject(createWordRequestDto.getWordName(),subject)){
                return new ResponseDto("해당 단어는 이미 존재하는 단어입니다.",createWordRequestDto.getWordName());
            }

            Word word = new Word();

            word.setName(createWordRequestDto.getWordName());
            word.setMeaning(createWordRequestDto.getMeaning());
            word.setSubject(subject);

            wordRepository.save(word);

            return new ResponseDto("단어 생성", word.getName());
        }
        else {
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

    public ResponseDto getWord(String wordName) {

        List<Word> wordList = wordRepository.findAllByName(wordName);
        List<GetWordListResponseDto> result = new ArrayList<>();

        for(Word w : wordList){
            GetWordListResponseDto temp = new GetWordListResponseDto();
            temp.setWordName(w.getName());
            temp.setMeaning(w.getMeaning());
            temp.setSubjectName(w.getSubject().getName());

            result.add(temp);
        }

        return new ResponseDto("단어 목록",result);
    }
}
