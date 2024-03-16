package com.ewhathon.notegather.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STUDENT_TB")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id", nullable = false)
    private Long id;

    @Column(name="student_email", nullable = false)
    private String email;

    @Column(name="student_nickname", nullable = false)
    private String nickname;

    @Column(name="student_password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Note> note;

    @Builder
    public Student(String email, String nickname, String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userRole = Objects.requireNonNullElse(userRole, userRole.ROLE_USER); //값이 없다면, ROLE_USER로 초기화
    }
}
