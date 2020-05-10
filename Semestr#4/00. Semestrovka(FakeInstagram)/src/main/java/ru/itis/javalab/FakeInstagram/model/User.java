package ru.itis.javalab.FakeInstagram.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements Serializable {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String hashPassword;

    private String password;

    private Role role;

    private State state;

    private String photoPath;

    private String confirm_code;

    private List<Post> postList;

    public String getPhotoPath() {
        return photoPath;
    }

    public User(Long id, String name, String surname, String email, String hashPassword, String password,
                Role role, State state, String photoPath, String confirm_code) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.hashPassword = hashPassword;
        this.password = password;
        this.role = role;
        this.state = state;
        this.photoPath = photoPath;
        this.confirm_code = confirm_code;
        this.postList = new ArrayList<>();
    }

    public void setPostToList(Post post){
        postList.add(post);
    }

}
