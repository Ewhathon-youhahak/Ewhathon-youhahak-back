package com.ewhathon.notegather.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizResponseDto {
    private List<QuizItem> quizItems; // 생성된 퀴즈 목록
}
