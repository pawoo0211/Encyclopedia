package com.example.encyclopedia.subject.controller;

import com.example.encyclopedia.subject.ResponseDto;
import com.example.encyclopedia.subject.dto.CreateWordRequestDto;
import com.example.encyclopedia.subject.dto.EditWordRequestDto;
import com.example.encyclopedia.subject.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @PostMapping("word/create")
    public ResponseDto createWord(@RequestBody CreateWordRequestDto createWordRequestDto){
        return wordService.createWord(createWordRequestDto);
    }

    @GetMapping("/{word}/get")
    public ResponseDto getWord(@PathVariable("word") String wordName){
        return wordService.getWord(wordName);
    }

    @PostMapping("{subject}/{word}/edit")
    public  ResponseDto editWord(@PathVariable("subject") String subjectName, @PathVariable("word") String wordName,
                                 @RequestBody EditWordRequestDto editWordRequestDto){
        return wordService.editWord(subjectName, wordName, editWordRequestDto);
    }

}
