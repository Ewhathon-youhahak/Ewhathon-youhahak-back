package com.ewhathon.notegather.service;

import com.ewhathon.notegather.domain.entity.Student;
import com.ewhathon.notegather.domain.repository.StudentRepository;
import com.ewhathon.notegather.web.dto.StudentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
