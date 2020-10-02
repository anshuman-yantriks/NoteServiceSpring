package com.example.repository;

import com.example.model.Note;
import com.example.model.NoteAndUserMapping;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MappingRepository extends ReactiveMongoRepository<NoteAndUserMapping,String> {
    Flux<NoteAndUserMapping> findByUserId(String validToken);
}
