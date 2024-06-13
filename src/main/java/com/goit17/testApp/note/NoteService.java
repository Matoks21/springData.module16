package com.goit17.testApp.note;

import com.goit17.testApp.repo.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.findAllNotes();
    }


    public void add(Note note) {
        noteRepository.addNote(note.getTitle(), note.getContent());
    }

    public void deleteById(long id) {
        noteRepository.deleteNoteById(id);
    }

    public void update(Note note) {
        noteRepository.updateNote(note.getId(), note.getTitle(), note.getContent());
    }

    public Note getById(long id) {
        return noteRepository.findNoteById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note with id " + id + " not found."));
    }

    public List<Note> searchByTitle(String keyword) {
        return noteRepository.findByTitleContaining(keyword);
    }

}

