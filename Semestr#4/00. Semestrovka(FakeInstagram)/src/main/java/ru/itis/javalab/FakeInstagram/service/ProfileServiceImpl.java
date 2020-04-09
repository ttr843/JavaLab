package ru.itis.javalab.FakeInstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.UserRepository;


@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void editProfile(UserDto userDto, MultipartFile multipartFile, User user) {
        String path;
        String password;
        if (multipartFile != null) {
            path = uploadService.saveFile(multipartFile);
        }else {
            path = user.getPhotoPath();
        }
        if(userDto.getPassword() == null ){
            password = user.getHashPassword();
        }else {
            password = passwordEncoder.encode(userDto.getPassword());
        }
        User newUser = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .hashPassword(password)
                .photoPath(path)
                .id(user.getId())
                .build();
        userRepository.updateProfileData(newUser);
    }
}
