package ru.javalab.servletshop.servlet;

import ru.javalab.context.ApplicationContext;
import ru.javalab.servletshop.Helpers.Helper;
import ru.javalab.servletshop.dto.UserDto;
import ru.javalab.servletshop.service.SignUpServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpServlet  extends HttpServlet {

    private ApplicationContext context;
    Map<String, Object> root = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.context = (ApplicationContext) servletContext.getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Helper.render(req,resp,"login.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignUpServiceImpl signUpService = context.getComponent(SignUpServiceImpl.class);
        UserDto userDto = signUpService.signUp(req.getParameter("login"),req.getParameter("password"));
        root.put("User",userDto);
        Helper.render(req,resp,"main.ftl",root);
    }
}
