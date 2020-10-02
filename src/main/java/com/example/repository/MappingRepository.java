package com.example.repository;

import com.example.model.NoteAndUserMapping;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MappingRepository extends ReactiveMongoRepository<NoteAndUserMapping,String> {
}
