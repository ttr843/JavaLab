package ru.javalab.servletshop.Helpers;

import freemarker.cache.WebappTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletRequest;

public class FreeMarkerConfig {
    private static Configuration cfg;

    public static Configuration getConfig(HttpServletRequest req) {
        if (cfg == null) {
            cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setTemplateLoader(new WebappTemplateLoader(req.getServletContext(),
                    "/WEB-INF/templates"));
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        }
        return cfg;
    }
}
