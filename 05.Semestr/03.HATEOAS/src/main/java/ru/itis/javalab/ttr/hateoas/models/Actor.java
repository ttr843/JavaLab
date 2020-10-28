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
public class Actor {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private String country;
    private double rate;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
