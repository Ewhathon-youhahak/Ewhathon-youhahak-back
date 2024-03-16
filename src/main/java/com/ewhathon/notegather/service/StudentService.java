package com.ewhathon.notegather.service;

import com.ewhathon.notegather.domain.entity.Student;
import com.ewhathon.notegather.domain.repository.StudentRepository;
import com.ewhathon.notegather.web.dto.StudentRequestDto;
import com.ewhathon.notegather.web.dto.StudentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentResponseDto getStudent(String name){
        Student student = studentRepository.findStudentByEmail(name);
        return new StudentResponseDto(student.getEmail(), student.getNickname());
    }

    @Transactional
    public String editStudent(String name, StudentRequestDto requestDto){
        Student student = studentRepository.findStudentByEmail(name);

        if(requestDto.getNickname() != null){
            student.setNickname(requestDto.getNickname());
        }
        if(requestDto.getPassword() != null){
            student.setPassword(requestDto.getPassword());
        }

        studentRepository.save(student);

        return "success";
    }
}
