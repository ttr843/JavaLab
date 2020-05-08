package ru.itis.javalab.FakeInstagram.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.FakeInstagram.dto.SignInDto;
import ru.itis.javalab.FakeInstagram.dto.TokenDto;
import ru.itis.javalab.FakeInstagram.restServices.interfaces.SignInService;

@RestController
@Profile("rest")
public class SignInController {

    @Autowired
    private SignInService signInService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInData) {
        System.out.println("AYE");
        return ResponseEntity.ok(signInService.signIn(signInData));
    }

}
