package com.example.controller;

import com.example.dto.NoteDto;
import com.example.dto.Response;
import com.example.service.INoteService;
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
    @RequestMapping(value = "note/{noteId}",method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Response>> deleteNote(@RequestHeader("Authentication") String token,@PathVariable("noteId") String noteId){
        return noteService.delete(token,noteId).map(note -> {
            Response response = new Response(200,"successfully deleted note",note);
            return ResponseEntity.ok(response);
        });
    }
    @RequestMapping(value = "note/collaborator/{noteId}",method = RequestMethod.POST)
    public Mono<ResponseEntity<Response>> addCollaborator(@RequestHeader("Authentication") String token,@PathVariable("noteId") String noteId){
        return noteService.addCollaborator(token,noteId).map(note -> {
            Response response = new Response(200,"successfully added Collaborator",note);
            return ResponseEntity.ok(response);
        });
    }
    @RequestMapping(value = "note/collaborator/{noteId}",method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Response>> deleteCollaborator(@RequestHeader("Authentication") String token,@PathVariable("noteId") String noteId){
        return noteService.deleteCollaborator(token,noteId).map(note -> {
            Response response = new Response(200,"successfully deleted Collaborator",note);
            return ResponseEntity.ok(response);
        });
    }
}
