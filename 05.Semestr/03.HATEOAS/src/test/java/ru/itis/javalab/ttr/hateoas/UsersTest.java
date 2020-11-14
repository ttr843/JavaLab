package ru.itis.javalab.ttr.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.javalab.ttr.hateoas.models.User;
import ru.itis.javalab.ttr.hateoas.services.ResourcesService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class UsersTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){

    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(get("http://localhost/users/4")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(getUser().getFirstName()))
                .andExpect(jsonPath("$.lastName").value(getUser().getLastName()))
                .andExpect(jsonPath("$.age").value(getUser().getAge()))
                .andExpect(jsonPath("$.country").value(getUser().getCountry()))
                .andDo(document("getUserById", responseFields(subsectionWithPath("_links").ignored(),
                        fieldWithPath("firstName").description("Имя"),
                        fieldWithPath("lastName").description("Фамилия"),
                        fieldWithPath("age").description("Возраст"),
                        fieldWithPath("country").description("Страна")
                )));
        mockMvc.
    }


    private User getUser(){
        return User.builder()
                .firstName("Ruslan")
                .lastName("Pashin")
                .age(20)
                .country("Russia")
                .build();
    }
}
