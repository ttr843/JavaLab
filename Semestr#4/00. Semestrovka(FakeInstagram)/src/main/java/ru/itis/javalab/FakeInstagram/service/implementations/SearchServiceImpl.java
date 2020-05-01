package ru.itis.javalab.FakeInstagram.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.FakeInstagram.dto.SearchDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.Sub;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.PostRepository;
import ru.itis.javalab.FakeInstagram.repository.interfaces.SubscriptionsRepository;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.SearchService;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

    @Override
    public List<Post> searchPosts(SearchDto searchDto) {
        return postRepository.findPostsByHashTag(searchDto.getKeyWord());
    }

    @Override
    public User searchProfile(SearchDto searchDto) {
        Optional<User> optionUser = userRepository.findByEmail(searchDto.getKeyWord());
        return optionUser.orElse(null);
    }

    @Override
    public List<User> searchSubscriptions(User user) {
        List<Sub> subs = subscriptionsRepository.findAllSubs(user.getId());
        List<User> users = new ArrayList<>();
        for(Sub sub:subs){
            Optional<User> optionalUser = userRepository.findById(sub.getIdToWho());
            optionalUser.ifPresent(users::add);
        }
        return users;
    }
}
