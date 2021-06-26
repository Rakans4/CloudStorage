package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final NotesService notesService;
    private final UserService userService;
    private final EncryptionService encryptionService;
    private final CredentialsService credentialsService;
    private final FileService fileService;

    public HomeController(NotesService notesService, UserService userService, EncryptionService encryptionService, CredentialsService credentialsService, FileService fileService) {
        this.notesService = notesService;
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialsService = credentialsService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model, Note note, Credentials credentials, File file) {
        Integer userid = this.userService.getUser(authentication.getPrincipal().toString()).getUserid();
        model.addAttribute("notes", this.notesService.getNotes(userid));
        model.addAttribute("allCredentials", this.credentialsService.getAllCredentials(userid));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("files", this.fileService.getFiles(authentication.getPrincipal().toString()));
        return "home";
    }

    @GetMapping("/error")
    public String errorHandling() {
        return "error";
    }
}
