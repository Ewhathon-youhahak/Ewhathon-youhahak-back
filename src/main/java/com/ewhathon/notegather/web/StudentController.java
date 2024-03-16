package com.ewhathon.notegather.web;

import com.ewhathon.notegather.config.CommonResponse;
import com.ewhathon.notegather.service.StudentService;
import com.ewhathon.notegather.web.dto.NoteListResponseDto;
import com.ewhathon.notegather.web.dto.StudentRequestDto;
import com.ewhathon.notegather.web.dto.StudentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/student")
    public StudentResponseDto getStudent(Authentication authentication){
        return studentService.getStudent(authentication.getName());
    }

    @PatchMapping("/student")
    public String editStudent(Authentication authentication, @RequestBody StudentRequestDto studentRequestDto){
        return studentService.editStudent(authentication.getName(), studentRequestDto);
    }
}
