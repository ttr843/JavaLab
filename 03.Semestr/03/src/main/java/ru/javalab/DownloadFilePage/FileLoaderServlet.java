package ru.javalab.DownloadFilePage;

import ru.javalab.FileDownloader.ThreadFileDownloader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class FileLoaderServlet extends HttpServlet {
    private ArrayList<String> splittedRequestParametr(String str){
        ArrayList<String> strs = new ArrayList<String>();
        if(str.contains("+") == false){
            strs.add(str);
            return strs;
        }
        while (str.contains("+") ) {
            strs.add(new String(str.substring(0, str.indexOf("+"))));
            str = str.substring(str.indexOf("+")+1, str.length());
            if(str.contains("+") == false){
                strs.add(str);
            }
        }
        return strs;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("url");
        ArrayList<String> urls = splittedRequestParametr(param);
        for (String x : urls
        ) {
            ThreadFileDownloader tfd = new ThreadFileDownloader(x);
        }
        response.sendRedirect("index.html");
    }
}
