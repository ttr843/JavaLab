package ru.itis.javalab.FakeInstagram.model;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder()
public class FileInfo {


    private Long id;

    private String storageFileName;

    private String originalFileName;

    private Long size;

    private String type;

    private String url;
}

