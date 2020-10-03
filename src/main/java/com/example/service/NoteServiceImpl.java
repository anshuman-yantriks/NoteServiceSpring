package com.example.service;

import com.example.dto.NoteDto;
import com.example.exception.NoteException;
import com.example.model.Note;
import com.example.model.NoteAndUserMapping;
import com.example.repository.MappingRepository;
import com.example.repository.NoteRepository;
import com.example.utility.WebClientFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class NoteServiceImpl implements INoteService{
    @Autowired
    private WebClientFactory webClientFactory;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    MappingRepository mappingRepository;

    @Value("${jwt.secret}")
    String secretKey;

    @Override
    public Mono<Note> save(NoteDto noteDto, String token) {
        Note note = new Note(noteDto);
        String userID = validToken(token);
        noteDto.setOwnerId(userID);
        return checkValidUserId(userID).flatMap(valid->{
            if (valid){
                return noteRepository.save(note).map(savedNote->{
                    NoteAndUserMapping mapping = new NoteAndUserMapping(savedNote.getNoteId(),userID);
                    mappingRepository.save(mapping).subscribe();
                    return savedNote;
                });
            }
            return Mono.error(new NoteException("user id is not valid"));
        });
    }

    @Override
    public Mono<Note> update(NoteDto noteDto, String token) {
        validToken(token);
        return noteRepository.findById(noteDto.getNoteId()).flatMap(note->{
            note.setDescription(noteDto.getDescription());
            note.setTitle(noteDto.getTitle());
            note.setUpdatedTime(LocalDateTime.now());
            return noteRepository.save(note);
        }).switchIfEmpty(Mono.error(new NoteException("note not found with given id")));
    }

    @Override
    public Flux<Note> getNotes(String token) {
        return mappingRepository.findByUserId(validToken(token)).flatMap(mapping->{
            return noteRepository.findById(mapping.getNoteId());
        });
    }

    @Override
    public Mono<Note> delete(String token,String noteId) {
        validToken(token);
        return noteRepository.findByNoteId(noteId).map(note->{
            mappingRepository.deleteByNoteId(noteId).subscribe();
            noteRepository.deleteByNoteId(noteId).subscribe();
            return note;
        }).switchIfEmpty(Mono.error(new NoteException("note not found with given note id")));
    }

    private Mono<Boolean> checkValidUserId(String id){
        return webClientFactory.getUserUrl()
                .get().uri("/user/valid/" + id)
                .retrieve().bodyToMono(Boolean.class);
    }
    private String validToken(String token){
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    @Override
    public Mono<NoteAndUserMapping> addCollaborator(String token, String noteId) {
        String userId = validToken(token);
        return mappingRepository.findByNoteIdAndUserId(noteId,userId)
                .flatMap(e -> Mono.error(new NoteException("Mapping already exist")))
                .switchIfEmpty(Mono.defer(()-> {
                    NoteAndUserMapping noteAndUserMapping = new NoteAndUserMapping(noteId,userId);
                    return mappingRepository.save(noteAndUserMapping);
                }))
                .cast(NoteAndUserMapping.class);
    }

    @Override
    public Mono<NoteAndUserMapping> deleteCollaborator(String token, String noteId) {
        return mappingRepository.findByNoteIdAndUserId(noteId,validToken(token))
                .map(mapping->{
                    mappingRepository.deleteById(mapping.getId()).subscribe();
                    return mapping;
                }).switchIfEmpty(Mono.error(new NoteException("no collaboration found")));
    }
}
