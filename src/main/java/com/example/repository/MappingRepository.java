package com.example.repository;

import com.example.model.Note;
import com.example.model.NoteAndUserMapping;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MappingRepository extends ReactiveMongoRepository<NoteAndUserMapping,String> {
    Flux<NoteAndUserMapping> findByUserId(String validToken);

    Flux<NoteAndUserMapping> deleteByNoteId(String noteId);

    Mono<NoteAndUserMapping> findByNoteIdAndUserId(String validToken, String noteId);
}
