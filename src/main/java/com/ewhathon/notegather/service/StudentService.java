package com.ewhathon.notegather.service;

import com.ewhathon.notegather.domain.entity.Student;
import com.ewhathon.notegather.domain.repository.StudentRepository;
import com.ewhathon.notegather.web.dto.StudentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;


}
