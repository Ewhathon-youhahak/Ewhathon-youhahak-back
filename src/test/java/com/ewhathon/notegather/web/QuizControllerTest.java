package com.ewhathon.notegather.web;

import com.ewhathon.notegather.service.GptService;
import com.ewhathon.notegather.web.dto.QuizResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(QuizController.class)
class QuizControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private GptService gptService;

    @Test
    void generateQuizTest() {
        // GptService의 generateQuiz 메소드 모킹
        QuizResponseDto mockResponse = new QuizResponseDto(); // 적절한 응답 객체 생성
        when(gptService.generateQuiz(any(Long.class))).thenReturn(Mono.just(mockResponse));

        // API 요청 테스트
        webTestClient.post().uri("/api/quizzes/generate?noteId=1")
                .exchange()
                //.expectStatus().isOk()
                .expectBody()
                .jsonPath("$.quizItems").exists(); // 응답 검증
    }
}

