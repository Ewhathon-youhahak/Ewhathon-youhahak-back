package com.ewhathon.notegather.service;

import com.ewhathon.notegather.domain.entity.Note;
import com.ewhathon.notegather.domain.repository.NoteRepository;
import com.ewhathon.notegather.web.dto.QuizItem;
import com.ewhathon.notegather.web.dto.QuizResponseDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;

@Service
public class GptService {
    private final WebClient webClient;
    private final NoteRepository noteRepository;
    @Value("${openai.api.key}")
    private String apiKey;

    @Autowired
    public GptService(@Value("${openai.api.key}") String apiKey, WebClient.Builder webClientBuilder, NoteRepository noteRepository) {
        this.apiKey = apiKey;
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1/chat").build();
        this.noteRepository = noteRepository;
    }

    public Mono<QuizResponseDto> generateQuiz(Long noteId) {
        // noteRepository.findById(noteId)의 결과를 Mono로 변환
        return Mono.justOrEmpty(noteRepository.findById(noteId))
                .flatMap(note -> {
                    String task = "You are an assistant designed to support college students with their studies." +
                            "Your task is to create quizzes based on the lecture notes summarized by the students, to aid their learning." +
                            "The quizzes must capture the core content of the notes and be presented in short-answer subjective format." +
                            "For each set of notes provided, you are to generate 5 quizzes with answers." +
                            "The language of the quizzes must match the language of the notes." +
                            "If the notes are in Korean, the quizzes must be in Korean; if the notes are in English," +
                            "the quizzes must also be in English." +
                            "Below is an example of lecture notes and the 5 quizzes that should be provided based on those notes." +
                            "You must not add any additional comments besides the 5 quizzes and answers. NO YAPPING.";
                    String oneshotNote =
                            "HERE IS THE NOTE CONTENT PROVIDED BY STUDENTS:\n" +
                                    "# Conventions of Love, Mechanisms of Gender, and Plot (2)\n\n" +
                                    "## Is Plot Value-neutral?\n" +
                                    "- Literary works are not created by a single genius but are composed of various literary conventions and traditions.\n" +
                                    "- Aesthetic and sensibilities are also formed by these conventions and traditions.\n" +
                                    "- Narrative techniques are part of a long-accumulated tradition.\n" +
                                    "- The ideology in literary works is actually created by the plot.\n" +
                                    "- Though plots may seem value-neutral and universal, they are inherently social, cultural, and ideological.\n\n" +
                                    "## Case Study: Female Protagonists\n" +
                                    "- Exceptional female protagonists often reinforce societal norms by being treated as 'exceptions'.\n" +
                                    "- Female protagonist narratives often adopt plots imposed by a male-dominated society.\n" +
                                    "- In romance novels, love and self-realization are often presented as mutually exclusive for women.\n" +
                                    "- New plot attempts: Since the late 19th century, female authors like Virginia Woolf have used various narrative strategies to escape traditional love plots.\n\n" +
                                    "## Mechanisms of Gender - Gender, Sexuality, Sex\n" +
                                    "- Mechanisms that enable sexual hierarchy and oppression.\n" +
                                    "- Include the body, nation, family, market, narrative, media, and education.\n" +
                                    "- Operating mechanisms: Sexualization, Mystification, Spatialization.\n" +
                                    "- Overcoming: Desexualization, Demystification, Despatialization.\n\n" +
                                    "## Narrative as a Mechanism of Gender\n" +
                                    "- An important device for reproducing sexual ideology.\n" +
                                    "- If narrative is viewed as a mechanism of gender, elements like plot can also be seen as mechanisms.\n" +
                                    "- Literature, TV dramas, and films often contribute to the reproduction of gender ideology.\n\n" +
                                    "## How Is Transition Possible?\n" +
                                    "- By rearranging composition, changing or leaving the 'land', and revealing the conspiracy.\n" +
                                    "- Creative reworking is important, as is adjustment, rearrangement, modification, supplementation, and expansion.\n\n" +
                                    "### The Penelopiad, Margaret Atwood\n" +
                                    "- A critique of society that oppresses women by venerating Penelope as a paragon of virtue.\n\n" +
                                    "# Western Love Narratives, Romance: Tristan and Isolde\n" +
                                    "- Romance genre originates from 12th-century French literature, involving knights, adventures, and noblewomen.\n" +
                                    "- Romance narratives often reflect and reinforce cultural and societal norms regarding love and gender roles.\n";
                    String[] oneshotQuiz = {
                            "Question 1: Are plots in literature value-neutral? Answer: No.",
                            "Question 2: Who strengthens gender norms, exceptional female protagonists or their portrayal as 'exceptions'? Answer: Portrayal as 'exceptions'.",
                            "Question 3: Who sought to escape traditional love plots, Virginia Woolf or male authors? Answer: Virginia Woolf.",
                            "Question 4: What is a mechanism for perpetuating sexual hierarchy? Answer: Sexualization.",
                            "Question 5: Can narratives both reproduce and transform gender ideologies? Answer: Yes."
                    };
                    String finalPrompt = task + "\n\n" + oneshotNote + "\n\n" +
                            "EXAMPLE QUIZZES BASED ON THE NOTES:\n" +
                            "- " + oneshotQuiz[0] + "\n" +
                            "- " + oneshotQuiz[1] + "\n" +
                            "- " + oneshotQuiz[2] + "\n" +
                            "- " + oneshotQuiz[3] + "\n" +
                            "- " + oneshotQuiz[4] + "\n\n" +
                            "YOUR TASK:\n" +
                            "Based on the lecture notes provided, generate 5 new short-answer quizzes that capture the core content of these notes. Ensure the quizzes and their answers are concise, clear, and adhere to the themes presented in the notes. The language of your quizzes should match that of the provided notes." +
                            "\n\nNOTE CONTENT TO BASE QUIZZES ON:\n" + note.getContent() +
                            "\n\nRemember, NO YAPPING. Just provide the 5 quizzes and their answers based on the content.";

                    List<Map<String, String>> messages = new ArrayList<>();
                    Map<String, String> userMessage = new HashMap<>();
                    userMessage.put("role", "user");
                    userMessage.put("content", finalPrompt);
                    messages.add(userMessage);

                    return this.webClient.post()
                            .uri("/completions")
                            .header("Authorization", "Bearer " + apiKey)
                            .bodyValue(Map.of(
                                    "model", "gpt-4-turbo-preview",
                                    "messages", messages,
                                    "max_tokens", 300,
                                    "temperature", 0.5,
                                    "n", 1
                            ))
                            .retrieve()
                            .bodyToMono(String.class)
                            .map(this::extractContentFromResponse) // GPT 응답에서 content 부분만 추출
                            .map(this::parseGptResponse); // 추출된 content를 파싱하여 QuizResponseDto 생성
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Note not found with id: " + noteId))); // Note가 없는 경우 에러 처리
    }
    private String extractContentFromResponse(String gptResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode rootNode = objectMapper.readTree(gptResponse);
            JsonNode choicesNode = rootNode.path("choices");
            if (choicesNode.isArray() && choicesNode.has(0)) {
                JsonNode firstChoice = choicesNode.get(0);
                JsonNode messageNode = firstChoice.path("message");
                return messageNode.path("content").asText();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 적절한 예외 처리 또는 로깅
        }
        return ""; // 또는 적절한 기본값 또는 예외 처리
    }

    private QuizResponseDto parseGptResponse(String gptResponse) {
        List<QuizItem> quizItems = new ArrayList<>();
        // "Question {number}: ... Answer: ..." 형식에 맞춘 정규 표현식
        Pattern pattern = Pattern.compile("Question \\d: (.*?) Answer: (.*?)(?=Question|$)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(gptResponse);

        while (matcher.find()) {
            String question = matcher.group(1).trim();
            String answer = matcher.group(2).trim();

            quizItems.add(QuizItem.builder()
                    .question(question)
                    .answer(answer)
                    .build());
        }

        return QuizResponseDto.builder()
                .quizItems(quizItems)
                .build();
    }
}