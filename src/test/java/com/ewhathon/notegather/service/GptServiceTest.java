package com.ewhathon.notegather.service;

import com.ewhathon.notegather.domain.entity.Note;
import com.ewhathon.notegather.domain.repository.NoteRepository;
import com.ewhathon.notegather.service.GptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.MediaType;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@WebFluxTest(GptService.class)
class GptServiceTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NoteRepository noteRepository;

    @Test
    void generateQuizTest() {
        // 가상의 Note 객체 설정
        Note mockNote = new Note();
        mockNote.setId(1L);
        mockNote.setContent("Test note content");
        when(noteRepository.findById(any())).thenReturn(Optional.of(mockNote));

        // GptService의 generateQuiz 메소드를 호출할 때 기대되는 JSON 응답
        String expectedJsonResponse = "{\"quizItems\": [{\"question\": \"Q1\", \"answer\": \"A1\"}]}";

        webTestClient.post().uri("/generate-quiz/{noteId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json(expectedJsonResponse);
    }
}

