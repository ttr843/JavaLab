package ru.itis.javalab.FakeInstagram.service.interfaces;

import ru.itis.javalab.FakeInstagram.dto.SearchDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;

import java.util.List;

public interface SearchService {
    List<Post> searchPosts(SearchDto searchDto);
    User searchProfile(SearchDto searchDto);
    List<User> searchSubscriptions(User user);
}
