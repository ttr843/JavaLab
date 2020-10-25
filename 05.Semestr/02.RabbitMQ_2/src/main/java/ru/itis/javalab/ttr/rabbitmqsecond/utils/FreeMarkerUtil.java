package ru.itis.javalab.ttr.rabbitmqsecond.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


public class FreeMarkerUtil {

    public static String getContract(String html, Map<String, Object> dataModel) throws Exception {
        Writer out = null;
        StringReader reader = null;
        try {
            out = new StringWriter();
            Configuration cfg = new Configuration(new Version(2, 3, 21));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setClassForTemplateLoading(FreeMarkerUtil.class, "/templates/");
            Template template = cfg.getTemplate(html);
            template.process(dataModel, out);
            reader = new StringReader(out.toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (out != null) {
                out.close();
            }
        }
        BufferedReader br = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null) {
            content.append(str);
        }
        return content.toString();
    }


}
