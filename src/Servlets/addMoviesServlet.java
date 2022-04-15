package Servlets;

import Classes.DBManager;
import Classes.Genre;
import Classes.Movies;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/addMovies")
public class addMoviesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (current_user != null && current_user.getRole().getName().equals("moderator")) {
            String nameOfMovie = request.getParameter("name");
            String img = request.getParameter("img");
            String video = request.getParameter("video");
            Genre genre = DBManager.getOneGenre(Long.parseLong(request.getParameter("genre")));
            if (genre != null) {
                    Movies movie = new Movies();
                    movie.setGenre(genre);
                    movie.setName(nameOfMovie);
                    movie.setImg(img);
                    movie.setVideo(video);
                    if (DBManager.addMovie(movie)) {
                        response.sendRedirect("addMovies?success");
                    }
            }
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user  = (User)request.getSession().getAttribute("CURRENT_USER");
        if(current_user!=null && current_user.getRole().getName().equals("moderator")) {
            ArrayList<Genre> genres = DBManager.getGenres();
            request.setAttribute("genres", genres);
            request.getRequestDispatcher("addMovies.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }

    }
}
