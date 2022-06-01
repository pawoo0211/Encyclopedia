package com.example.encyclopedia.subject.service;

import com.example.encyclopedia.subject.ResponseDto;
import com.example.encyclopedia.subject.domain.Subject;
import com.example.encyclopedia.subject.domain.SubjectRepository;
import com.example.encyclopedia.subject.domain.Word;
import com.example.encyclopedia.subject.domain.WordRepository;
import com.example.encyclopedia.subject.dto.CreateWordRequestDto;
import com.example.encyclopedia.subject.dto.EditWordRequestDto;
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

    public ResponseDto editWord(String subjectName, String wordName, EditWordRequestDto editWordRequestDto) {

        if(!subjectRepository.existsByName(subjectName)){
            /* existsBy~는 존재할 경우 참 */
            /* Optional<Subject> subject = Optional.ofNullable(subjectRepository.findByName(subjectName));
            *  는 사용 불가능
            */
            return new ResponseDto("FAIL","해당 주제는 존재하지 않습니다.");
        }

        Subject subject = subjectRepository.findByName(subjectName);

        if(!wordRepository.existsByNameAndSubject(wordName, subject)){
            return new ResponseDto("FAIL","해당 주제에 맞는 단어는 존재하지 않습니다.");
        }

        Word word = wordRepository.findByNameAndSubject(wordName, subject);

        if(editWordRequestDto.getType() == "name"){
            word.setName(editWordRequestDto.getValue());
            wordRepository.save(word);
            return new ResponseDto("SUCCESS", "단어 이름 변경 완료");
        }

        word.setMeaning(editWordRequestDto.getValue());
        wordRepository.save(word);
        return new ResponseDto("SUCCESS", "단어 의미 변경 완료");


    }

    public ResponseDto deleteWord(String subjectName, String wordName) {
        if(!subjectRepository.existsByName(subjectName)){
            /* existsBy~는 존재할 경우 참 */
            /* Optional<Subject> subject = Optional.ofNullable(subjectRepository.findByName(subjectName));
             *  는 사용 불가능
             */
            return new ResponseDto("FAIL","해당 주제는 존재하지 않습니다.");
        }

        Subject subject = subjectRepository.findByName(subjectName);

        if(!wordRepository.existsByNameAndSubject(wordName, subject)){
            return new ResponseDto("FAIL","해당 주제에 맞는 단어는 존재하지 않습니다.");
        }

        Word word = wordRepository.findByNameAndSubject(wordName, subject);

        wordRepository.delete(word); // 자식 테이블인 "word"를 삭제하는 것은 가능

        return new ResponseDto("단어 삭제", wordName);
    }
}
