package com.ewhathon.notegather.web.dto;

import com.ewhathon.notegather.domain.entity.Note;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String lectureName;
    private String professorName;
    private String studentName;

    @Builder
    public NoteResponseDto(Note note){
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.createdDate = note.getCreatedDate();
        this.lectureName = note.getLecture().getName();
        this.professorName = note.getLecture().getProfessor();
        this.studentName = note.getStudent().getNickname();
    }
}
