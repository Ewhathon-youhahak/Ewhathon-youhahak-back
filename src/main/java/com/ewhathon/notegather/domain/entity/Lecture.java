package com.ewhathon.notegather.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LECTURE_TB")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lecture_id")
    private Long id;

    @Column(name="lecture_name", nullable = false)
    private String name;

    @Column(name="lecture_professor", nullable = false)
    private String professor;

    @OneToMany(mappedBy = "lecture")
    @JsonIgnore
    private List<Note> note;

    @Builder
    public Lecture(String name, String professor){
        this.name = name;
        this.professor = professor;
    }
}
