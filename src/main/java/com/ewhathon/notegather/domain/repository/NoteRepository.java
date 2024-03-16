package com.ewhathon.notegather.domain.repository;

import com.ewhathon.notegather.domain.entity.Note;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findNotesByLecture_NameContaining(String lectureName);
    List<Note> findNotesByLecture_ProfessorContaining(String professorName);
}
