package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/home")
public class NotesController {
    private NotesService notesService;
    private UserService userService;

    public NotesController(NotesService notesService, UserService userService) {
        this.notesService = notesService;
        this.userService = userService;
    }

    @PostMapping("add-note")
    public String addNote (Authentication authentication, Note note, Model model) {
        if(note.getNoteid() != null) {
            this.notesService.updateNote(note);
            model.addAttribute("success", true);
        } else {
            note.setUserid(this.userService.getUser(authentication.getPrincipal().toString()).getUserid());
            notesService.addNote(note);
            model.addAttribute("success", true);
        }
        return "result";
    }

    @GetMapping("/delete-note/{noteid}")
    public String deleteNote(@PathVariable Integer noteid, Model model) {
        this.notesService.deleteNote(noteid);
        model.addAttribute("success", true);
        return "result";
    }
}
