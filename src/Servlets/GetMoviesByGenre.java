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

@WebServlet(value = "/byGenre")
public class GetMoviesByGenre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String genre = request.getParameter("genre");
        System.out.println(genre);
        ArrayList<Movies> movies = DBManager.getMoviesByGenre(genre);
        System.out.println(movies);
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/moviesbygenre.jsp").forward(request,response);

    }
}
