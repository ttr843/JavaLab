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
public class Resource {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double rate;
    private String country;
    private int year;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    @Enumerated(value = EnumType.STRING)
    private ResourceType resourceType;
    @OneToMany(mappedBy = "resource")
    private List<Comment> comments;
    @OneToMany(mappedBy = "resource")
    private List<Favorite> favorites;
    @OneToMany(mappedBy = "resource")
    private List<Actor> actors;

    public void updateRate(double rate){
        this.setRate(rate);
    }

    public void setTypeUnknown(){
        this.setResourceType(ResourceType.UNKNOWN);
    }
}
