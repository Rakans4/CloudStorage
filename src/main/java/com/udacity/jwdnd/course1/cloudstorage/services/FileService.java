package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }

    public List<File> getFiles(String username) {
        Integer userId = this.userService.getUserByUsername(username).getUserid();
        return this.fileMapper.getAllFiles(userId);
    }

    public boolean isFileNameAvailable(String filename, String username) {
        Integer userId = this.userService.getUserByUsername(username).getUserid();
        File file = this.fileMapper.getFileByFileName(filename, userId);
        return file ==  null;
    }

    public File getFile(Integer fileid) {
        return this.fileMapper.getFile(fileid);
    }

    public void deleteFile(Integer fileid) {
        this.fileMapper.delete(fileid);

    }

    public void addFile(MultipartFile file, String username) throws IOException {

        Integer userId = this.userService.getUserByUsername(username).getUserid();

        byte[] fileData = file.getBytes();
        String contentType = file.getContentType();
        String fileSize = String.valueOf(file.getSize());
        String fileName = file.getOriginalFilename();

        this.fileMapper.insert(new File(fileName, contentType, fileSize, userId, fileData));
    }
}
