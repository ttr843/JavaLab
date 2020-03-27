package ru.itis.javalab.fileload.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Upload {
    private Long id;
    private String originalName;
    private String generatedName;
    private String type;
    private double size;
    private String path;

    public Upload(String originalName, String generatedName, String type, double size, String path) {
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.type = type;
        this.size = size;
        this.path = path;
    }

}
