package com.example.controller;

import com.example.dto.NoteDto;
import com.example.dto.Response;
import com.example.service.INoteService;
import io.jsonwebtoken.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class NoteController {
    @Autowired
    INoteService noteService;

    @RequestMapping(value = "note",method = RequestMethod.POST)
    public Mono<ResponseEntity<Response>> saveNote(@RequestHeader("Authentication") String token,@Valid @RequestBody NoteDto noteDto){
        return noteService.save(noteDto,token).map(note -> {
            Response response = new Response(200,"successfully created note",note);
            return ResponseEntity.ok(response);
        });
    }

    @RequestMapping(value = "note",method = RequestMethod.PUT)
    public Mono<ResponseEntity<Response>> updateNote(@RequestHeader("Authentication") String token,@Valid @RequestBody NoteDto noteDto){
        return noteService.update(noteDto,token).map(note -> {
            Response response = new Response(200,"successfully updated note",note);
            return ResponseEntity.ok(response);
        });
    }
    @RequestMapping(value = "note",method = RequestMethod.GET)
    public Flux<NoteDto> getNotes(@RequestHeader("Authentication") String token){
        return noteService.getNotes(token).map(note -> {
            return new NoteDto(note);
        });
    }
}
