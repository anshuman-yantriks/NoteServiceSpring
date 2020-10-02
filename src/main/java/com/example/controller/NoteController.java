package com.example.controller;

import com.example.dto.NoteDto;
import com.example.dto.Response;
import com.example.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class NoteController {
    @Autowired
    INoteService noteService;

    @RequestMapping(value = "note",method = RequestMethod.POST)
    public Mono<ResponseEntity<Response>> saveNote(@RequestBody NoteDto noteDto){
        return noteService.save(noteDto).map(note -> {
            Response response = new Response(200,"successfully created note",note);
            return ResponseEntity.ok(response);
        });
    }
}
