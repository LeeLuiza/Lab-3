package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.IOException;


@WebServlet(urlPatterns = {"/cool-servlet", "/my-cool-servlet/*"})
public class App extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (request.getParameter("path") == null) {
            path = "D:\\Folder";
        }
        request.setAttribute("path", path);

        File directory = new File(path); //папки
        File[] folder = directory.listFiles(File::isDirectory);
        request.setAttribute("folder", folder);

        File[] files = directory.listFiles(File::isFile); //файлы
        request.setAttribute("files", files);

        request.getRequestDispatcher("files.jsp").forward(request, response);
    }
}
