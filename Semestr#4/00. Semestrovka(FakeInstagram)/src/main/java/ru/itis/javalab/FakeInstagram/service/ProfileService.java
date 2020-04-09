package ru.itis.javalab.FakeInstagram.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.User;

public interface ProfileService {
    void editProfile(UserDto userDto, MultipartFile multipartFile, User user);
}
