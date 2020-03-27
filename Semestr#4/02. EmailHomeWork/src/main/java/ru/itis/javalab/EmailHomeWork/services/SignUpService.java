package ru.itis.javalab.EmailHomeWork.services;

import freemarker.template.Template;
import ru.itis.javalab.EmailHomeWork.dto.SignUpDto;


public interface SignUpService {
    void signUp(SignUpDto form, Template template);
}
