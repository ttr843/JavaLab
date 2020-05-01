package ru.itis.javalab.FakeInstagram.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.Sub;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.PostRepository;
import ru.itis.javalab.FakeInstagram.repository.interfaces.SubscriptionsRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.NewsService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final SubscriptionsRepository subscriptionsRepository;

    private final PostRepository postRepository;

    @Autowired
    public NewsServiceImpl(SubscriptionsRepository subscriptionsRepository, PostRepository postRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> showNews(User user) {
        List<Sub> subs = subscriptionsRepository.findAllSubs(user.getId());
        List<Post> posts = new ArrayList<>();
        for (Sub sub : subs) {
            posts.addAll(postRepository.findPostsByUserId(sub.getIdToWho()));
        }
        return posts;
    }
}
