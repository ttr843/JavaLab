package ru.itis.javalab.FakeInstagram.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;

public interface SignUpService {
    UserDto login(UserDto userDto);

    UserDto signUp(UserDto userDto, MultipartFile multipartFile);
}
