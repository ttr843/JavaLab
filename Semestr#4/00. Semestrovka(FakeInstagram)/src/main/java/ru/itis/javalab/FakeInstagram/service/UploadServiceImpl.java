package ru.itis.javalab.FakeInstagram.service;

import lombok.SneakyThrows;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.Utils.FileStorageUtil;
import ru.itis.javalab.FakeInstagram.model.FileInfo;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class UploadServiceImpl implements UploadService {
    @Value("${storage.upload.path}")
    private String storagePath;

    @Autowired
    private FileStorageUtil fileStorageUtil;


    @Override
    public String saveFile(MultipartFile multipartFile) {
        FileInfo fileInfo = fileStorageUtil.convertFromMultipart(multipartFile);
        fileStorageUtil.copyToStorage(multipartFile, fileInfo.getStorageFileName());
        return  "http://localhost/files/" + fileInfo.getStorageFileName();
    }

    @SneakyThrows
    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {

        InputStream inputStream = new FileInputStream(new java.io.File(storagePath + fileName));
        org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
    }


    @Override
    public void downloadFile(String fileName) {

    }
}
