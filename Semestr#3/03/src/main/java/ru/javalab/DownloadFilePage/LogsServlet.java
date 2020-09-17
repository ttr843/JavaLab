package ru.javalab.DownloadFilePage;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class LogsServlet implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String method = req.getMethod();
        Date date = new Date(System.currentTimeMillis());
        PrintWriter pw = new PrintWriter(new File("log.txt"));
        pw.println("Date: " + date.toString());
        pw.println("Method: " + method);
        pw.println("Address: " + req.getRequestURI());
        pw.println("client IP address: " + req.getRemoteAddr());
        pw.println("local IP address:" + req.getLocalAddr());
        pw.flush();
        pw.close();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
