package com.ewhathon.notegather.web;

import com.ewhathon.notegather.web.dto.QuizResponseDto;
import com.ewhathon.notegather.service.GptService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final GptService gptService;

    public QuizController(GptService gptService) {
        this.gptService = gptService;
    }

    @PostMapping("/generate")
    public Mono<QuizResponseDto> generateQuiz(@RequestParam Long noteId) {
        // GptService의 generateQuiz 메소드가 Mono<QuizResponseDto>를 반환
        return gptService.generateQuiz(noteId);
    }
}
