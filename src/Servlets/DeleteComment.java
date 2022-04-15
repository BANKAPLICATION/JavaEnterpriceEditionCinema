package Servlets;

import Classes.Comments;
import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteComment")
public class DeleteComment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (user!=null) {
            Long comment_id = 0L;
            Long movie_id = 0L;
            try {
                comment_id = Long.parseLong(request.getParameter("id"));
                movie_id = Long.parseLong(request.getParameter("movie_id"));

                Comments comment = DBManager.getOneComment(comment_id, movie_id);
                if (comment!=null) {
                    DBManager.deleteComment(comment);
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            response.sendRedirect("/login");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
