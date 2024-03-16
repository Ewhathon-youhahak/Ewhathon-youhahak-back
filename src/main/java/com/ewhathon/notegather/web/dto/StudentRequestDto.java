package com.ewhathon.notegather.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDto {
    private String email;
    private String nickname;
    private String password;
}
