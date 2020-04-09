package ru.itis.javalab.FakeInstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {
    private String text;

    private String place;

    public String getText() {
        return text;
    }

    public String getPlace() {
        return place;
    }
}
