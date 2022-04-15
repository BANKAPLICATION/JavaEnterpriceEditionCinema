package Servlets;

import Classes.DBManager;
import Classes.Movies;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/addComment")
public class addCommentsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long movieId = 0L;
        try {
            User currentUser = (User)request.getSession().getAttribute("CURRENT_USER");
            if (currentUser!=null) {
                movieId = Long.parseLong(request.getParameter("id"));
                Movies movie = DBManager.getOneMovie(movieId);
                if (movie!=null) {
                    String comment = request.getParameter("comment");
                    DBManager.addComment(currentUser.getId(), comment, movie.getId());
                }
            }else {
                response.sendRedirect("/login");
            }
        }
        catch (Exception e) {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
