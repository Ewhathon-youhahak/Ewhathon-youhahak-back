package com.ewhathon.notegather.domain.repository;

import com.ewhathon.notegather.domain.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    
}
