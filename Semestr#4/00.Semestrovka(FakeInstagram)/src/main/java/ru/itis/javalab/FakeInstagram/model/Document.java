package ru.itis.javalab.FakeInstagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.File;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String path;
    private Long size;

    @Transient
    private String fileName;

    @Transient
    private File sourceFile;

    @PostLoad
    public void loadFile() {
        sourceFile = new File(path);
        fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Load file for " + fileName);
    }

    @PreUpdate
    public void updateFileInformation() {
        this.path = sourceFile.getPath();
        this.size = sourceFile.length();
        this.fileName = sourceFile.getName().substring(0, sourceFile.getName().lastIndexOf("."));
        log.info("Update file information for " + fileName);
    }
}
