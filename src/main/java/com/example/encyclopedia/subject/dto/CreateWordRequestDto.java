package com.example.encyclopedia.subject.dto;

import lombok.Data;

@Data
public class CreateWordRequestDto {
    String subjectName;
    String wordName;
    String meaning;
}
