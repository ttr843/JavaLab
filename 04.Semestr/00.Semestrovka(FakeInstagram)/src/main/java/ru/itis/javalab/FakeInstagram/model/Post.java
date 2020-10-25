package ru.itis.javalab.FakeInstagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Post {
    private long id;

    private long idPublicator;

    private String text;

    private long likeOfPost;

    private String date;

    private String place;

    private String photo;


}
