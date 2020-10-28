package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.ttr.hateoas.models.Subscription;

public interface SubscriptionsRepository extends JpaRepository<Subscription,Long> {
}
