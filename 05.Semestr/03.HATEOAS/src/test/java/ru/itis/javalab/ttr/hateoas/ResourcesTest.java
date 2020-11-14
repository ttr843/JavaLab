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
import ru.itis.javalab.ttr.hateoas.models.Genre;
import ru.itis.javalab.ttr.hateoas.models.Resource;
import ru.itis.javalab.ttr.hateoas.models.ResourceType;
import ru.itis.javalab.ttr.hateoas.repositories.ResourcesRepository;
import ru.itis.javalab.ttr.hateoas.services.ResourcesService;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ResourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResourcesService resourcesService;

    @MockBean
    private ResourcesRepository resourcesRepository;

    @BeforeEach
    public void setUp(){
        when(resourcesService.setTypeUnknown(1L)).thenReturn(getResource());
        when(resourcesRepository.findMaxRate()).thenReturn(getResource());
    }

    @Test
    public void resourcePutTypeUnknownTest() throws Exception {
        mockMvc.perform(put("/resources/1/setTypeUnknown")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.year").value(getResource().getYear()))
                .andExpect(jsonPath("$.rate").value(getResource().getRate()))
                .andExpect(jsonPath("$.country").value(getResource().getCountry()))
                .andExpect(jsonPath("$.name").value(getResource().getName()))
                .andExpect(jsonPath("$.genre").value(getResource().getGenre().toString()))
                .andExpect(jsonPath("$.resourceType").value(getResource().getResourceType().toString()))
                .andDo(document("putTypeUnknown", responseFields(subsectionWithPath("_links").ignored(),
                        fieldWithPath("year").description("Год ресурса"),
                        fieldWithPath("rate").description("Рейтинг ресурса"),
                        fieldWithPath("country").description("Страна ресурса"),
                        fieldWithPath("name").description("Название ресурса"),
                        fieldWithPath("genre").description("Жанр ресурса"),
                        fieldWithPath("resourceType").description("Тип ресурса(Фильм,Сериал,МультФильм,Неизвестно)")
                )));
    }

//    @Test
//    public void getResourceByGenre() throws Exception{
//        mockMvc.perform(get("/resources/search/byGenre?genre=ACTION")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.year").value(getResource().getYear()))
//                .andExpect(jsonPath("$.rate").value(getResource().getRate()))
//                .andExpect(jsonPath("$.country").value(getResource().getCountry()))
//                .andExpect(jsonPath("$.name").value(getResource().getName()))
//                .andExpect(jsonPath("$.genre").value(getResource().getGenre().toString()))
//                .andExpect(jsonPath("$.resourceType").value(getResource().getResourceType().toString()))
//                .andDo(document("getResourceByGenre", responseFields(subsectionWithPath("_links").ignored(),
//                        fieldWithPath("year").description("Год ресурса"),
//                        fieldWithPath("rate").description("Рейтинг ресурса"),
//                        fieldWithPath("country").description("Страна ресурса"),
//                        fieldWithPath("name").description("Название ресурса"),
//                        fieldWithPath("genre").description("Жанр ресурса"),
//                        fieldWithPath("resourceType").description("Тип ресурса(Фильм,Сериал,МультФильм,Неизвестно)")
//                )));
//    }

//    @Test
//    public void resourceGetMaxRate() throws Exception {
//        mockMvc.perform(get("/resources/search/maxRate")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.year").value(getResource().getYear()))
//                .andExpect(jsonPath("$.rate").value(getResource().getRate()))
//                .andExpect(jsonPath("$.country").value(getResource().getCountry()))
//                .andExpect(jsonPath("$.name").value(getResource().getName()))
//                .andExpect(jsonPath("$.genre").value(getResource().getGenre().toString()))
//                .andExpect(jsonPath("$.resourceType").value(getResource().getResourceType().toString()))
//                .andDo(document("getMaxRate", responseFields(subsectionWithPath("_links").ignored(),
//                        fieldWithPath("year").description("Год ресурса"),
//                        fieldWithPath("rate").description("Рейтинг ресурса"),
//                        fieldWithPath("country").description("Страна ресурса"),
//                        fieldWithPath("name").description("Название ресурса"),
//                        fieldWithPath("genre").description("Жанр ресурса"),
//                        fieldWithPath("resourceType").description("Тип ресурса(Фильм,Сериал,МультФильм,Неизвестно)")
//                )));
//    }

//    @Test
//    public void getResourceById()throws Exception{
//        mockMvc.perform(get("/resources/8")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.year").value(getResource().getYear()))
//                .andExpect(jsonPath("$.rate").value(getResource().getRate()))
//                .andExpect(jsonPath("$.country").value(getResource().getCountry()))
//                .andExpect(jsonPath("$.name").value(getResource().getName()))
//                .andExpect(jsonPath("$.genre").value(getResource().getGenre().toString()))
//                .andExpect(jsonPath("$.resourceType").value(getResource().getResourceType().toString()))
//                .andDo(document("getResourceById", responseFields(subsectionWithPath("_links").ignored(),
//                        fieldWithPath("year").description("Год ресурса"),
//                        fieldWithPath("rate").description("Рейтинг ресурса"),
//                        fieldWithPath("country").description("Страна ресурса"),
//                        fieldWithPath("name").description("Название ресурса"),
//                        fieldWithPath("genre").description("Жанр ресурса"),
//                        fieldWithPath("resourceType").description("Тип ресурса(Фильм,Сериал,МультФильм,Неизвестно)")
//                )));
//    }


    private Resource getResource(){
        return  Resource.builder()
                .id(1L)
                .year(2000)
                .rate(10.0)
                .country("Russia")
                .resourceType(ResourceType.UNKNOWN)
                .genre(Genre.ACTION)
                .name("Test")
                .build();
    }
}
