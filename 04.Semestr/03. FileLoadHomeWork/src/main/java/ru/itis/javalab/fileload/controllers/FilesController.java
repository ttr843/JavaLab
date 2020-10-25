package ru.itis.javalab.fileload.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.fileload.models.Upload;
import ru.itis.javalab.fileload.repositories.UploadRepository;
import ru.itis.javalab.fileload.services.UploadService;

@Controller
public class FilesController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public String init(@ModelAttribute("model") ModelMap modelMap) {
        //List<File> files = fileRepository.findAll();
        //modelMap.addAttribute("files",files);
        return "files";
    }

    @RequestMapping(value = "/files/upload", method = RequestMethod.GET)
    public String returnTemplateForUpload(){
        return "file_upload";
    }

    @RequestMapping(value = "/files",consumes = "multipart/form-data",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("mail") String mail) {
        Upload upload = uploadService.saveFile(multipartFile,mail);
        return "redirect:/files";
    }
    @RequestMapping(value = "/files/{file-name:.+}", method = RequestMethod.GET)
    public String getFile(@PathVariable("file-name") String fileName) {
        uploadService.downloadFile(fileName);
        return "redirect:/files";
    }
}
