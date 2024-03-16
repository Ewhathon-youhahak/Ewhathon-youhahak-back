package com.ewhathon.notegather.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponseDto {
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String lectureName;
    private String professorName;
}
