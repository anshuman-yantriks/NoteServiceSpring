package com.example.repository;

import com.example.model.Note;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends ReactiveMongoRepository<Note,String> {
}
