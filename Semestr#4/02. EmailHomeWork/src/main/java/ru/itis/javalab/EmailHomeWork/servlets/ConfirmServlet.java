package ru.itis.javalab.EmailHomeWork.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.EmailHomeWork.Helper.FreeMarkerRender;
import ru.itis.javalab.EmailHomeWork.services.ConfirmService;
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
@WebServlet("/confirm")
public class ConfirmServlet extends HttpServlet {

    private ConfirmService confirmService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ApplicationContext servletContext = (ApplicationContext) context.getAttribute("Context");
        confirmService = servletContext.getBean(ConfirmService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isConfirmed = confirmService.confirm(req.getParameter("confirmCode"));
        Map<String, Object> root = new HashMap<>();
        root.put("isConfirmed", isConfirmed);
        FreeMarkerRender.render(req,resp,"confirm.ftl",root);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
