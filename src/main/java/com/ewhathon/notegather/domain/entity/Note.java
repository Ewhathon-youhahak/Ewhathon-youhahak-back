package com.ewhathon.notegather.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NOTE_TB")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="note_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name="note_title", nullable = false)
    private String title;

    @Column(name="note_content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name="note_created_date")
    private LocalDateTime createdDate;

    @Builder
    public Note(Student student, Lecture lecture, String title, String content, LocalDateTime createdDate){
        this.student = student;
        this.lecture = lecture;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

    public void setLectureName(String lectureName){
        this.lecture.setName(lectureName);
    }

    public void setProfessorName(String professorName){
        this.lecture.setProfessor(professorName);
    }
}
