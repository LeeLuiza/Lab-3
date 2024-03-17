package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/files"})
public class Folders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        String password = (String) request.getSession().getAttribute("password");
        String path = (String) request.getSession().getAttribute("path");

        Cookie[] cookies = request.getCookies();
        if (login == null) {
            login = cookies[0].getValue();
            password = cookies[1].getValue();
        }

        if (path == null || path.equals("D:\\Folder/")) {
            path = "D:\\Folder\\" + login;
        }
        request.getSession().setAttribute("path", path);
        request.getSession().setAttribute("login", login);

        File directory = new File(path); //папки
        File[] folder = directory.listFiles(File::isDirectory);
        request.setAttribute("folder", folder);

        File[] files = directory.listFiles(File::isFile); //файлы
        request.setAttribute("files", files);

        request.getRequestDispatcher("files.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sessionId = request.getSession().getId();
        AccountService.deleteSession(sessionId);
        String url = request.getRequestURL().toString();
        response.sendRedirect(url.substring(0, url.lastIndexOf('/')) + "/");
    }
}
