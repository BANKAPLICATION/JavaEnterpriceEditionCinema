package Servlets;

import Classes.DBManager;
import Classes.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/details")
public class DetailsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Long id = 0L;
        try {
            id = Long.parseLong(request.getParameter("id"));
            Movies movie = DBManager.getOneMovie(id);
            if (movie!=null) {
                request.setAttribute("movie", movie);
                    request.getRequestDispatcher("moviewatch.jsp").forward(request,response);
            }
        }
        catch (Exception e) {

        }

    }
}
