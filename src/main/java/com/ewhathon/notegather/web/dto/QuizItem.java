package com.ewhathon.notegather.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizItem {
    private String question; // 퀴즈 질문
    private String answer;   // 퀴즈 답변
}
