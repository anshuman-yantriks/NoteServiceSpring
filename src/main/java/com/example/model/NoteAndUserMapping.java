package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("note_and_user_mapping")
public class NoteAndUserMapping {
    @Id
    private String id;
    @Field
    private String noteId;
    @Field("user_id")
    private String userId;

    public NoteAndUserMapping() {
    }

    public NoteAndUserMapping(String noteId, String userId) {
        this.noteId = noteId;
        this.userId = userId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
