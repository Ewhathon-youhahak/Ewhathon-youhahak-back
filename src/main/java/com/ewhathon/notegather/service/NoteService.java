package com.ewhathon.notegather.service;
import com.ewhathon.notegather.domain.entity.Lecture;
import com.ewhathon.notegather.domain.entity.Note;
import com.ewhathon.notegather.domain.entity.Student;
import com.ewhathon.notegather.domain.repository.LectureRepository;
import com.ewhathon.notegather.domain.repository.NoteRepository;
import com.ewhathon.notegather.domain.repository.StudentRepository;
import com.ewhathon.notegather.web.dto.NoteListResponseDto;
import com.ewhathon.notegather.web.dto.NoteRequestDto;
import com.ewhathon.notegather.web.dto.NoteResponseDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoteService {

    @Autowired
    private final NoteRepository noteRepository;
    private final LectureRepository lectureRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public Long createNote(NoteRequestDto noteRequestDto, String email){
        Optional<Lecture> lecture = lectureRepository.findLectureByNameAndProfessor(noteRequestDto.getLectureName(),noteRequestDto.getProfessorName());
        Student student = studentRepository.findStudentByEmail(email);

        if(lecture.isEmpty()){
            lecture = Optional.ofNullable(Lecture.builder()
                    .name(noteRequestDto.getLectureName())
                    .professor(noteRequestDto.getProfessorName())
                    .build());
            lectureRepository.save(lecture.get());
        }

        Note note = Note.builder()
                .title(noteRequestDto.getTitle())
                .content(noteRequestDto.getContent())
                .lecture(lecture.get())
                .student(student)
                .createdDate(LocalDateTime.now())
                .build();

        noteRepository.save(note);

        return note.getId();
    }

    public List<NoteListResponseDto> getAllNotes(){
        List<Note> notes = noteRepository.findAll();

        return notes.stream()
                .map(note -> new NoteListResponseDto(note.getId(), note.getTitle(), note.getLecture().getName(), note.getLecture().getProfessor()))
                .collect(Collectors.toList());
    }

    public List<NoteListResponseDto> searchNotes(String type, String keyword){
        List<Note> notes;
        if(type.equals("lecture")){
            notes =  noteRepository.findNotesByLecture_NameContaining(keyword);
        }else if(type.equals("professor")){
            notes = noteRepository.findNotesByLecture_ProfessorContaining(keyword);
        }else{
            throw new IllegalArgumentException("Invalid search type: " + type);
        }
        return notes.stream()
                .map(note -> new NoteListResponseDto(note.getId(), note.getTitle(), note.getLecture().getName(), note.getLecture().getProfessor()))
                .collect(Collectors.toList());
    }

    public List<NoteListResponseDto> getStudentNotes(String email){
        Student student = studentRepository.findStudentByEmail(email);
        List<Note> notes = noteRepository.findNotesByStudent_Id(student.getId());
        return notes.stream()
                .map(note -> new NoteListResponseDto(note.getId(), note.getTitle(), note.getLecture().getName(), note.getLecture().getProfessor()))
                .collect(Collectors.toList());
    }

    public NoteResponseDto getNote(Long noteId) throws Exception{
        return new NoteResponseDto(noteRepository.findById(noteId).orElseThrow(()-> new Exception("노트를 찾을 수 없습니다.")));
    }

    @Transactional
    public Long editNote(Long noteId, NoteRequestDto requestDto) throws Exception{
        Note note = noteRepository.findById(noteId)
                .orElseThrow(()-> new NullPointerException("노트를 찾을 수 없습니다."));

        if(requestDto.getTitle() != null){
            note.setTitle(requestDto.getTitle());
        }
        if(requestDto.getContent() != null){
            note.setContent(requestDto.getContent());
        }
        if(requestDto.getLectureName() != null){
            note.setLectureName(requestDto.getLectureName());
        }
        if(requestDto.getProfessorName() != null){
            note.setProfessorName(requestDto.getProfessorName());
        }

        noteRepository.save(note);

        return noteId;
    }

    public String deleteNote(Long noteId){
        noteRepository.deleteById(noteId);

        if(noteRepository.findById(noteId).isEmpty()){
            return "success";
        }
        return "fail";
    }
}
