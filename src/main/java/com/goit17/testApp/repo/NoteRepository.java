package com.goit17.testApp.repo;


import com.goit17.testApp.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n")
    List<Note> findAllNotes();

    @Modifying
    @Transactional
    @Query("INSERT INTO Note (title, content) VALUES (:title, :content)")
    void addNote(@Param("title") String title, @Param("content") String content);

    @Modifying
    @Transactional
    @Query("DELETE FROM Note n WHERE n.id = :id")
    void deleteNoteById(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE Note n SET n.title = :title, n.content = :content WHERE n.id = :id")
    void updateNote(@Param("id") long id, @Param("title") String title, @Param("content") String content);

    @Query("SELECT n FROM Note n WHERE n.id = :id")
    Optional<Note> findNoteById(@Param("id") long id);

    @Query("SELECT n FROM Note n WHERE n.title LIKE %:keyword%")
    List<Note> findByTitleContaining(String keyword);

    @Query(value = "SELECT * FROM note WHERE id = :id", nativeQuery = true)
    Note findNoteByIdNative(long id);

}
