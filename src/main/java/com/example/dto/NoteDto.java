package com.example.dto;

import com.example.model.Note;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


public class NoteDto {
    private String noteId;
    @NotBlank(message = "description cannot be blank")
    private String ownerId;
    @NotBlank(message = "description cannot be blank")
    @Length(min = 3, max = 30, message = "description should have at least 3 characters and max 30 characters")
    private String description;
    @NotBlank(message = "title cannot be blank")
    @Length(min = 3, max = 30, message = "title should have at least 3 characters and max 30 characters")
    private String title;

    public NoteDto() {
    }

    public NoteDto(Note note) {
        this.noteId = note.getNoteId();
        this.ownerId = note.getOwnerId();
        this.description = note.getDescription();
        this.title = note.getTitle();
    }


    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
