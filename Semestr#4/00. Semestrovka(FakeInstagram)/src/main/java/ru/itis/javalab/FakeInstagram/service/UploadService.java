package ru.itis.javalab.FakeInstagram.service;

import lombok.SneakyThrows;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


public interface UploadService {
    String saveFile(MultipartFile multipartFile);

    void writeFileToResponse(String fileName, HttpServletResponse response);

    void downloadFile(String fileName);

}
