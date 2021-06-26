package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;


import java.io.IOException;

@Controller
@RequestMapping("/home")
public class FilesController {
    private final FileService fileService;

    public FilesController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/add-file")
    public String addFile(@RequestParam("fileUpload")MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {
        String username = authentication.getPrincipal().toString();
        if (this.fileService.isFileNameAvailable(fileUpload.getOriginalFilename(), username)) {
            this.fileService.addFile(fileUpload, username);
            model.addAttribute("success", true);
        }
        else {
            model.addAttribute("errorWithMessage", true);
            model.addAttribute("errorMessage", "File Name already exist.");
        }

        return "result";
    }

    @GetMapping("/file/{fileid}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer fileid) {
        File file = this.fileService.getFile(fileid);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContenttype()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(new ByteArrayResource(file.getFiledata()));
    }

    @GetMapping("delete-file/{fileid}")
    public String deleteFile(@PathVariable Integer fileid, Model model) {
        this.fileService.deleteFile(fileid);
        model.addAttribute("success", true);
        return "result";
    }
}
