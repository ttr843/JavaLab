package ru.itis.javalab.FakeInstagram.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.SubDto;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.SubscriptionsRepository;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.UploadService;
import ru.itis.javalab.FakeInstagram.service.interfaces.ProfileService;


@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionsRepository subscriptionsRepository;

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

    @Override
    public void subscribe(SubDto subDto, User user) {
        subscriptionsRepository.subscribe(subDto.getSubId(),user.getId());
    }

    @Override
    public void deleteSub(SubDto subDto, User user) {
        subscriptionsRepository.deleteSub(subDto.getSubId(),user.getId());
    }
}
