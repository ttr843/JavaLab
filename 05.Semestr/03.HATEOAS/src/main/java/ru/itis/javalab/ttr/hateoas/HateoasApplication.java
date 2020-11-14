package ru.itis.javalab.ttr.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.javalab.ttr.hateoas.models.*;
import ru.itis.javalab.ttr.hateoas.repositories.*;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(HateoasApplication.class, args);
        ActorsRepository actorsRepository = applicationContext.getBean(ActorsRepository.class);
        CommentsRepository commentsRepository = applicationContext.getBean(CommentsRepository.class);
        FavoritesRepository favoritesRepository = applicationContext.getBean(FavoritesRepository.class);
        ResourcesRepository resourcesRepository = applicationContext.getBean(ResourcesRepository.class);
        UsersRepository usersRepository = applicationContext.getBean(UsersRepository.class);
        SubscriptionsRepository subscriptionsRepository = applicationContext.getBean(SubscriptionsRepository.class);
        Subscription subscription1 = Subscription.builder()
                .date("20.10.2020")
                .subscriptionType(SubscriptionType.PREMIUM)
                .build();
        Subscription subscription2 = Subscription.builder()
                .date("21.10.2020")
                .subscriptionType(SubscriptionType.LUXE)
                .build();
        Subscription subscription3 = Subscription.builder()
                .date("21.10.2020")
                .subscriptionType(SubscriptionType.LUXE)
                .build();
        subscriptionsRepository.saveAll(asList(subscription1, subscription2,subscription3));
        User user1 = User.builder()
                .age(20)
                .country("Russia")
                .firstName("Ruslan")
                .lastName("Pashin")
                .subscription(subscription1)
                .build();
        User user2 = User.builder()
                .age(20)
                .country("Russia")
                .firstName("Roman")
                .lastName("Shurkin")
                .subscription(subscription2)
                .build();
        User user3 = User.builder()
                .age(20)
                .country("Russia")
                .firstName("Sergey")
                .lastName("Ryzhakov")
                .subscription(subscription3)
                .build();
        usersRepository.saveAll(asList(user1, user2, user3));
        Resource resource1 = Resource.builder()
                .genre(Genre.ACTION)
                .name("Горбатая гора")
                .rate(1.0)
                .resourceType(ResourceType.FILM)
                .year(2000)
                .country("America")
                .build();
        Resource resource2 = Resource.builder()
                .genre(Genre.ACTION)
                .name("Peaky Blinders")
                .rate(10.0)
                .resourceType(ResourceType.SERIAL)
                .year(2016)
                .country("America")
                .build();
        Resource resource3 = Resource.builder()
                .genre(Genre.FANTASY)
                .name("Гадкий я")
                .rate(8.0)
                .resourceType(ResourceType.CARTOON)
                .year(2010)
                .country("America")
                .build();
        resourcesRepository.saveAll(asList(resource1, resource2, resource3));
        Favorite favorite1 = Favorite.builder()
                .resource(resource1)
                .user(user1)
                .value(true)
                .build();
        Favorite favorite2 = Favorite.builder()
                .resource(resource2)
                .user(user2)
                .value(true)
                .build();
        Favorite favorite3 = Favorite.builder()
                .resource(resource3)
                .user(user3)
                .value(false)
                .build();
        favoritesRepository.saveAll(asList(favorite1, favorite2, favorite3));
        Comment comment1 = Comment.builder()
                .resource(resource1)
                .date("20.10.2020")
                .text("nice")
                .user(user1)
                .build();
        Comment comment2 = Comment.builder()
                .resource(resource2)
                .date("20.10.2020")
                .text("bad")
                .user(user2)
                .build();
        commentsRepository.saveAll(asList(comment1, comment2));
        Actor actor1 = Actor.builder()
                .age(30)
                .firstName("Kilian")
                .lastName("Merfi")
                .rate(10.0)
                .country("America")
                .resource(resource2)
                .build();
        Actor actor2 = Actor.builder()
                .age(99)
                .firstName("Утенок")
                .lastName("Гадкий")
                .rate(9.3)
                .country("America")
                .resource(resource3)
                .build();
        actorsRepository.saveAll(asList(actor1, actor2));
    }

}
