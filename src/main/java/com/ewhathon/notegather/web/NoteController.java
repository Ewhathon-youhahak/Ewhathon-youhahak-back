package com.ewhathon.notegather.web;

import com.ewhathon.notegather.service.NoteService;
import com.ewhathon.notegather.web.dto.NoteListResponseDto;
import com.ewhathon.notegather.web.dto.NoteRequestDto;
import com.ewhathon.notegather.web.dto.NoteResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @PostMapping("/notes")
    @Operation(summary = "필기 생성 API", description = "필기 제목과 내용으로 필기를 생성하는 API 입니다.")
    public Long createNote(Authentication authentication, @RequestBody NoteRequestDto noteRequestDto){
        return noteService.createNote(noteRequestDto, authentication.getName());
    }

    @GetMapping("/notes")
    public List<NoteListResponseDto> getNotes(Authentication authentication, @RequestParam(required = false) String searchType, @RequestParam(required = false) String searchTerm) throws Exception{
        if(searchType != null || searchTerm != null){
            return noteService.searchNotes(searchType, searchTerm);
        }else{
            return noteService.getAllNotes();
        }
    }

    @GetMapping("/notes/{noteId}")
    public NoteResponseDto getNote(Authentication authentication, @PathVariable("noteId") Long noteId) throws Exception{
        return noteService.getNote(noteId);
    }

    @Transactional
    @PatchMapping("notes/{noteId}")
    public Long editNote(Authentication authentication, @PathVariable("noteId") Long noteId, @RequestBody NoteRequestDto requestDto) throws Exception{
        return noteService.editNote(noteId, requestDto);
    }

    @Transactional
    @DeleteMapping("notes/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable("noteId") Long noteId){
        return noteService.deleteNote(noteId);
    }
}
