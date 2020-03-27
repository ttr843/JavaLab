package ru.itis.javalab.fileload.aspects;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.javalab.fileload.models.Upload;
import ru.itis.javalab.fileload.services.EmailService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Aspect
@Component
@EnableAspectJAutoProxy
public class EmailSendingAspect {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * ru.itis.javalab.fileload.services.UploadServiceImpl.saveFile(..))")
    public void callAtUploadService() {

    }

    @AfterReturning(pointcut = "callAtUploadService()", returning = "upload")
    public void afterCallMethodSaveFile(JoinPoint jp, Upload upload) {
        try {
            Map model = new HashMap<>();
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftl");
            String link = "http://localhost:8080/files/" + upload.getGeneratedName() + "." + upload.getType();
            model.put("usermail", jp.getArgs()[1].toString());
            model.put("link", link);
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            emailService.sendMail("Your file", link, jp.getArgs()[1].toString());
            logger.info("Sended letter to email: " + jp.getArgs()[1].toString());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
