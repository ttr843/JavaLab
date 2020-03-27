package ru.itis.javalab.EmailHomeWork.Helper;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import ru.itis.javalab.EmailHomeWork.config.FreeMarkerConfig;

public class FreeMarkerRender {

    public static void render(HttpServletRequest request,
                              HttpServletResponse response,
                              String path,
                              Map<String, Object> root) {
        Configuration cfg = FreeMarkerConfig.getConfig(request);
        try {
            Template tmpl = cfg.getTemplate(path);
            try {
                tmpl.process(root, response.getWriter());
            } catch (TemplateException e) {
                throw new IllegalArgumentException();
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }
}
