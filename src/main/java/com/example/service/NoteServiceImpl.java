package com.example.service;

import com.example.dto.NoteDto;
import com.example.dto.UserDto;
import com.example.exception.NoteException;
import com.example.model.Note;
import com.example.model.NoteAndUserMapping;
import com.example.repository.MappingRepository;
import com.example.repository.NoteRepository;
import com.example.utility.WebClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

@Service
public class NoteServiceImpl implements INoteService{
    @Autowired
    private WebClientFactory webClientFactory;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    MappingRepository mappingRepository;

    @Override
    public Mono<Note> save(NoteDto noteDto) {
        Note note = new Note(noteDto);
        return checkValidUserId(noteDto.getOwnerId()).flatMap(valid->{
            if (valid){
                return noteRepository.save(note).map(savedNote->{
                    NoteAndUserMapping mapping = new NoteAndUserMapping(savedNote.getNoteId(),savedNote.getOwnerId());
                    mappingRepository.save(mapping).subscribe();
                    return savedNote;
                });
            }
            return Mono.error(new NoteException("user id is not valid"));
        });
    }
    private Mono<Boolean> checkValidUserId(String id){
        return webClientFactory.getUserUrl()
                .get().uri("/user/valid/" + id)
                .retrieve().bodyToMono(Boolean.class);
    }
}
