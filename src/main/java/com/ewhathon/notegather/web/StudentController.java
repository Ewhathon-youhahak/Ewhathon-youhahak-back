package com.ewhathon.notegather.web;

import com.ewhathon.notegather.service.StudentService;
import com.ewhathon.notegather.web.dto.StudentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/students")
    public Long createStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.join(studentRequestDto);
    }
}
