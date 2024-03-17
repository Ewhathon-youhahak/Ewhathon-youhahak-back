# NOTEGATHER

![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/5810374a-ef56-450f-b080-9f535357b9c6)


## **서비스 소개**

학생들이 모여서(GATHER) 필기노트(NOTE)를 공유할 수 있는 플랫폼인 NOTEGATHER는, 학생들이 강의를 들으며 작성한 노트 필기를 공유하고 퀴즈를 풀며 자가학습할 수 있도록 하는 공유형 학습 플랫폼입니다. 노트 필기를 공유받고자 하는 학생들과 노트 기반의 퀴즈를 통해 학습한 것을 점검하고자 하는 학생들이 유용하게 사용할 수 있습니다.

## 팀원 소개

---

| 정수완 | 박서연 | 오서영 | 박현아 | 황채원 |
| --- | --- | --- | --- | --- |
| 국제학과/융합콘텐츠학과 | 융합콘텐츠학과/컴퓨터공학 | 사이버보안학과 | 컴퓨터공학 | 컴퓨터공학 |
| ![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/b93931f7-021e-4d2f-ac1b-2f31ced90be9) | ![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/fac5983b-7df5-4615-bf8b-7bc16b6fea05) | ![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/ec8c5fa2-2ec8-43cc-bf33-856e8f32b9d2) | ![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/d1937470-9e99-4580-a7b1-671b2b47ba1e) | ![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/01a35cce-0ad5-4bad-a2e1-6d2a14ca1b8a) |
| Design/Service Design | Frontend Developer | Frontend Developer | Backend Developer | AI, Backend Developer |

## 기술 스택

### Frontend

- React
- Vercel

### Backend

- Springboot 3.2.3
- MySQL 8.0
- Github Actions
- Docker/Google Artifact Registry
- Google Cloud Storage/Cloud Run
- OpenAI API

## 프로젝트 구조
```
└─src
    ├─main
    │  ├─java.com.ewhathon.notegather
    │  │              │  NotegatherApplication.java
    │  │              │
    │  │              ├─auth
    │  │              │  │  AuthController.java
    │  │              │  │  AuthDetails.java
    │  │              │  │  AuthDetailService.java
    │  │              │  │  AuthService.java
    │  │              │  │
    │  │              │  ├─dto
    │  │              │  │      LoginRequestDto.java
    │  │              │  │      RegisterRequestDto.java
    │  │              │  │
    │  │              │  ├─jwt
    │  │              │  │      JwtAuthenticationFilter.java
    │  │              │  │      JwtAuthorizationFilter.java
    │  │              │  │      JwtToken.java
    │  │              │  │      JwtTokenProvider.java
    │  │              │  │
    │  │              │  └─security
    │  │              │          SecurityConfig.java
    │  │              │
    │  │              ├─config
    │  │              │      CommonResponse.java
    │  │              │
    │  │              ├─domain
    │  │              │  ├─entity
    │  │              │  │      Lecture.java
    │  │              │  │      Note.java
    │  │              │  │      Student.java
    │  │              │  │      UserRole.java
    │  │              │  │
    │  │              │  └─repository
    │  │              │          LectureRepository.java
    │  │              │          NoteRepository.java
    │  │              │          StudentRepository.java
    │  │              │
    │  │              ├─service
    │  │              │      GptService.java
    │  │              │      LectureService.java
    │  │              │      NoteService.java
    │  │              │      StudentService.java
    │  │              │
    │  │              └─web
    │  │                  │  NoteController.java
    │  │                  │  QuizController.java
    │  │                  │  StudentController.java
    │  │                  │
    │  │                  └─dto
    │  │                          NoteListRequestDto.java
    │  │                          NoteListResponseDto.java
    │  │                          NoteRequestDto.java
    │  │                          NoteResponseDto.java
    │  │                          QuizItem.java
    │  │                          QuizRequestDto.java
    │  │                          QuizResponseDto.java
    │  │                          StudentRequestDto.java
    │  │                          StudentResponseDto.java
    │  │
    │  └─resources\
    └─test\
```

## 아키텍처

![image](https://github.com/Ewhathon-youhahak/Ewhathon-youhahak-back/assets/67725652/a1687c5f-7883-4123-abf1-dbf1d68f5780)


## 데모 영상 링크

https://www.youtube.com/watch?v=O9Y7png3yps&feature=youtu.be
