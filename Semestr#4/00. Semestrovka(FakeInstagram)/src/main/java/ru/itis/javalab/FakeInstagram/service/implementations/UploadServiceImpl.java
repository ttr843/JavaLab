package ru.itis.javalab.FakeInstagram.service.implementations;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.Utils.FileStorageUtil;
import ru.itis.javalab.FakeInstagram.model.FileInfo;
import ru.itis.javalab.FakeInstagram.service.interfaces.UploadService;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;

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
