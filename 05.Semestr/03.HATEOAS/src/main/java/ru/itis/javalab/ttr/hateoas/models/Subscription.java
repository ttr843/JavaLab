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
public class Subscription {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne(mappedBy = "subscription")
    private User user;
    @Enumerated(value = EnumType.STRING)
    private SubscriptionType subscriptionType;
    private String date;
}
