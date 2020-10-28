package ru.itis.javalab.ttr.hateoas.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private long id;
    private String text;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
    private String date;
}
