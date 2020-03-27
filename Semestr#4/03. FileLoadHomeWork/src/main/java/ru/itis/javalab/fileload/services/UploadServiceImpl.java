package ru.itis.javalab.fileload.services;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.javalab.fileload.models.Upload;
import ru.itis.javalab.fileload.repositories.UploadRepository;

import javax.servlet.ServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private ServletRequest servletRequest;

    @Autowired
    private UploadRepository uploadRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private EmailService emailService;

    @Override
    public Upload saveFile(MultipartFile multipartFile, String email) {
        try {
            String pathDir = servletRequest.getServletContext().getRealPath(
                    environment.getProperty("storage.upload.path"));
            File dir = new File(pathDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String original_name = FilenameUtils.getBaseName(multipartFile.getOriginalFilename());
            String generated_name = UUID.randomUUID().toString();
            File dest = new File(pathDir + generated_name + "." + extension);
            multipartFile.transferTo(dest);
            String path = environment.getProperty("storage.upload.path") + generated_name + "." +
                    extension;
            Upload upload = new Upload(original_name, generated_name, extension, multipartFile.getSize(), path);
            uploadRepository.save(upload);
//            Map model = new HashMap<>();
//            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
//            String link = "http://localhost:8080/files/" + generated_name + "." + extension;
//            model.put("usermail", email);
//            model.put("link", link);
//            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
//            emailService.sendMail("Your file", link, email);
            return upload;
        } catch (IOException e) {
            throw new IllegalStateException(e);
//        } catch (TemplateException e) {
//            throw new IllegalStateException(e);
        }
    }

    @Override
    public void downloadFile(String fileName) {
        try {
            String name = FilenameUtils.getBaseName(fileName);
            Optional<Upload> file = uploadRepository.find(name);
            if (file.isPresent()) {
                InputStream is = new BufferedInputStream(new FileInputStream(servletRequest.
                        getServletContext().getRealPath(file.get().getPath())));
                String pathDir = servletRequest.getServletContext().getRealPath(
                        environment.getProperty("storage.download.path"));
                File dir = new File(pathDir);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                OutputStream out = new BufferedOutputStream(new FileOutputStream(
                        new File(pathDir + file.get().getOriginalName() + "." + file.get().getType())));
                byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = is.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void showAllFiles() {

    }
}
