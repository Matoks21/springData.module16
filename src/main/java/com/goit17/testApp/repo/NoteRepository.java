package com.goit17.testApp.repo;


import com.goit17.testApp.note.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.title LIKE %:keyword%")
    List<Note> findByTitleContaining(@Param("keyword") String keyword);


}