package com.ewhathon.notegather.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequestDto {
    private String title;
    private String content;
    private String lectureName;
    private String professorName;
}
