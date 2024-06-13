package com.goit17.testApp.note;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.goit17.testApp.repo.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListAll() {
        List<Note> notes = new ArrayList<>();
        when(noteRepository.findAll()).thenReturn(notes);

        List<Note> result = noteService.listAll();

        assertEquals(notes, result);
    }

    @Test
    void testAdd() {
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        noteService.add(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testDeleteById() {
        long id = 1L;
        doNothing().when(noteRepository).deleteById(id);

        noteService.deleteById(id);

        verify(noteRepository, times(1)).deleteById(id);
    }

    @Test
    void testUpdate() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Updated Title");
        note.setContent("Updated Content");

        noteService.update(note);

        verify(noteRepository, times(1)).save(note);
    }

    @Test
    void testGetById() {
        long id = 1L;
        Note note = new Note();
        when(noteRepository.findById(id)).thenReturn(Optional.of(note));

        Note result = noteService.getById(id);

        assertEquals(note, result);
    }

    @Test
    void testGetByIdNotFound() {
        long id = 1L;
        when(noteRepository.findById(id)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            noteService.getById(id);
        });

        assertEquals("Note with id " + id + " not found.", exception.getMessage());
    }

    @Test
    void testSearchByTitle() {
        String keyword = "Test";
        List<Note> notes = new ArrayList<>();
        when(noteRepository.findByTitleContaining(keyword)).thenReturn(notes);

        List<Note> result = noteService.searchByTitle(keyword);

        assertEquals(notes, result);
    }
}