package com.example.encyclopedia.subject.controller;

import com.example.encyclopedia.subject.ResponseDto;
import com.example.encyclopedia.subject.dto.CreateWordRequestDto;
import com.example.encyclopedia.subject.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    @PostMapping("word/create")
    public ResponseDto createWord(@RequestBody CreateWordRequestDto createWordRequestDto){
        return wordService.createWord(createWordRequestDto);
    }

}
