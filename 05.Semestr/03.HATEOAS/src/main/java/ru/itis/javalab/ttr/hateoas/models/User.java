package ru.itis.javalab.ttr.hateoas.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    int age;
    private String country;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;


}
