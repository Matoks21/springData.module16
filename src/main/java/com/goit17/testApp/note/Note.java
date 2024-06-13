package com.goit17.testApp.note;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity

public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "Title is required")
    @Size(min = 1, message = "Title must be at least 1 character long")
    private String title;


    @Column(nullable = false)
    @NotEmpty(message = "Content is required")
    @Size(min = 1, message = "Content must be at least 1 character long")
    private String content;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}