package ru.itis.javalab.FakeInstagram.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.SubDto;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.User;

public interface ProfileService {
    void editProfile(UserDto userDto,User user);
    void subscribe(SubDto subDto,User user);
    void deleteSub(SubDto subDto,User user);
    void updatePhotoAvatar(MultipartFile multipartFile,User user);
}
