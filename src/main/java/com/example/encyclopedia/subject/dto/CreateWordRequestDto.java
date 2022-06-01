package com.example.encyclopedia.subject.dto;

import lombok.Data;

@Data
public class CreateWordRequestDto {
    String SubjectName;
    String wordName;
    String meaning;
}
