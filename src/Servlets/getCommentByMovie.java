package Servlets;

import Classes.Comments;
import Classes.DBManager;
import Classes.Movies;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(value = "/getCommentByMovie")
public class getCommentByMovie extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long movie_id = 0L;
        try {

            movie_id = Long.parseLong(request.getParameter("id"));

            Movies movie = DBManager.getOneMovie(movie_id);

            if (movie!=null) {
               ArrayList<Comments> comments =  DBManager.getAllCommentsByMovie(movie_id);
               if (comments!=null) {
                    Gson gson = new Gson();
                   PrintWriter out = response.getWriter();
                   out.print(gson.toJson(comments));
               }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
