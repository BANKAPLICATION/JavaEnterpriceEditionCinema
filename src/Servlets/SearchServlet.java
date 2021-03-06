package Servlets;

import Classes.DBManager;
import Classes.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String word = request.getParameter("search");
        System.out.println(word);
        ArrayList<Movies> movies = DBManager.getMoviesBySearch(word);
        System.out.println(movies);
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/search.jsp").forward(request,response);
    }
}
