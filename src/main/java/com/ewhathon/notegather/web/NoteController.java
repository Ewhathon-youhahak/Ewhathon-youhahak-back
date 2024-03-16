package com.ewhathon.notegather.web;

import com.ewhathon.notegather.service.NoteService;
import com.ewhathon.notegather.web.dto.NoteListResponseDto;
import com.ewhathon.notegather.web.dto.NoteRequestDto;
import com.ewhathon.notegather.web.dto.NoteResponseDto;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/notes")
    public Long createNote(Authentication authentication, @RequestBody NoteRequestDto noteRequestDto){
        return noteService.createNote(noteRequestDto, authentication.getName());
    }

    @GetMapping("/notes")
    public List<NoteListResponseDto> getNotes(Authentication authentication){
        return noteService.getNotes();
    }

    @GetMapping("/notes/{noteId}")
    public NoteResponseDto getNote(Authentication authentication, @PathVariable("noteId") Long noteId) throws Exception{
        return noteService.getNote(noteId);
    }

    @PatchMapping("notes/{noteId}")
    public Long editNote(Authentication authentication, @PathVariable("noteId") Long noteId, @RequestBody NoteRequestDto requestDto) throws Exception{
        return noteService.editNote(noteId, requestDto);
    }

    @DeleteMapping("notes/{noteId}")
    public void deleteNote(Authentication authentication, @PathVariable("noteId") Long noteId){
        noteService.deleteNote(noteId);
    }
}
