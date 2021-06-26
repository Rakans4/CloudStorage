package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NotesService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public void addNote(Note note) {
        noteMapper.insert(note);
    }

    public void deleteNote(Integer noteid) {
        this.noteMapper.delete(noteid);
    }

    public void updateNote(Note note) {
        this.noteMapper.update(note);
    }

    public List<Note> getNotes(Integer userid) {
        return noteMapper.getAllNotes(userid);
    }

    public Note getNote(String title) {
        return noteMapper.getOneNote(title);
    }
}
