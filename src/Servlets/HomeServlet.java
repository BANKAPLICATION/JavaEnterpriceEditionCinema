package Servlets;

import Classes.DBManager;
import Classes.Language;
import Classes.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        Cookie[] cookies = request.getCookies();
        String lang = "en";
        for (Cookie c: cookies) {
            if (c.getName().equals("lang")) {
                lang = c.getValue();
            }
        }
        if (lang.equals("ru")) {
            ArrayList<Movies> movies = DBManager.getAllMovies();
            if (movies != null) {
                request.setAttribute("lang", Language.russian);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }else {
            ArrayList<Movies> movies = DBManager.getAllMovies();
            if (movies != null) {
                request.setAttribute("lang", Language.english);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
