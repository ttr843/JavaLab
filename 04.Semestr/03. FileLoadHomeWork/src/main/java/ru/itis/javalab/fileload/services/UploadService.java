package ru.itis.javalab.fileload.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.fileload.models.Upload;

public interface UploadService {
    Upload saveFile(MultipartFile multipartFile, String email);
    void downloadFile(String fileName);
    void showAllFiles();
}
