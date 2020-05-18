package ru.itis.javalab.FakeInstagram.service.interfaces;

import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;

import java.util.List;

public interface NewsService {
    List<Post> showNews(User user);
}
