package ru.itis.javalab.FakeInstagram.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.FakeInstagram.service.interfaces.FilesService;

@RestController
@Profile("mvc")
public class FileController {

    @Autowired
    private FilesService filesService;


    @GetMapping("/file/init")
    public ResponseEntity<?> init() {
        filesService.init();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/file/convert")
    public ResponseEntity<?> convert() {
        filesService.convert();
        return ResponseEntity.ok().build();
    }

}
