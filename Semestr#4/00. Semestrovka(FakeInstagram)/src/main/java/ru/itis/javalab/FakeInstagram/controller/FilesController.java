package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.javalab.FakeInstagram.service.interfaces.UploadService;

import javax.servlet.http.HttpServletResponse;


@Controller
@Profile("mvc")
public class FilesController {
    @Autowired
    private UploadService uploadService;

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName,
                        HttpServletResponse response) {
        uploadService.writeFileToResponse(fileName, response);
    }

}
