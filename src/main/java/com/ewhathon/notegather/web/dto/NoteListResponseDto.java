package com.ewhathon.notegather.web.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteListResponseDto {
    private Long id;
    private String title;
    private String lectureName;
    private String professorName;
}
