package ru.itis.javalab.EmailHomeWork.servlets;


import org.springframework.context.ApplicationContext;
import ru.itis.javalab.EmailHomeWork.Helper.FreeMarkerRender;
import ru.itis.javalab.EmailHomeWork.config.FreeMarkerConfig;
import ru.itis.javalab.EmailHomeWork.dto.SignUpDto;
import ru.itis.javalab.EmailHomeWork.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext servletContext = (ApplicationContext) context.getAttribute("Context");
        signUpService = servletContext.getBean(SignUpService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Map<String, Object> root = new HashMap<>();
        FreeMarkerRender.render(req,resp,"sign_up.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpDto signUpDto = new SignUpDto(req.getParameter("name"),req.getParameter("email"),
                req.getParameter("password"));
        signUpService.signUp(signUpDto, FreeMarkerConfig.getConfig(req).getTemplate("mail.ftl"));
        resp.sendRedirect("/signUp");
    }

}
