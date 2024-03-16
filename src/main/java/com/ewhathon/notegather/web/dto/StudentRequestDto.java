package com.ewhathon.notegather.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDto {
    private String email;
    private String nickname;
    private String password;
}
