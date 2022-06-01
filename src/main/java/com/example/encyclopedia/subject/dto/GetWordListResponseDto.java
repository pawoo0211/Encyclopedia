package com.example.encyclopedia.subject.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class GetWordListResponseDto {
    String wordName;
    String meaning;
    String subjectName;

}
