package com.ewhathon.notegather.web;

import com.ewhathon.notegather.service.NoteService;
import com.ewhathon.notegather.web.dto.NoteRequestDto;
import com.ewhathon.notegather.web.dto.NoteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/notes")
    public NoteResponseDto createNote(@RequestBody NoteRequestDto noteRequestDto){
        return noteService.createNote(noteRequestDto);
    }
}
