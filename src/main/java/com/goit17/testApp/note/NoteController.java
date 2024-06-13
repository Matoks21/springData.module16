package com.goit17.testApp.note;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note/list")
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "note/list";
    }

    @PostMapping("/note/delete")
    public String deleteNote(@RequestParam Long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/note/edit")
    public String editNoteForm(@RequestParam Long id, Model model) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/edit";
    }

    @PostMapping("/note/edit")
    public String editNote(@ModelAttribute @Valid Note note, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "note/edit";
        }
        noteService.update(note);
        return "redirect:/note/list";
    }

    @GetMapping("/note/add")
    public String addNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "note/add";
    }

    @PostMapping("/note/add")
    public String addNote(@ModelAttribute @Valid Note note, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "note/add";
        }
        noteService.add(note);
        return "redirect:/note/list";
    }

    @GetMapping("/note/search")
    public String searchNotes(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Note> notes;
        if (keyword != null && !keyword.isEmpty()) {
            notes = noteService.searchByTitle(keyword);
        } else {
            notes = noteService.listAll();
        }
        model.addAttribute("notes", notes);
        return "note/list";
    }


}