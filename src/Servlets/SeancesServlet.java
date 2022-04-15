package Servlets;

import Classes.DBManager;
import Classes.Movies;
import Classes.Seances;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/seances")
public class SeancesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Long id = 0L;
         try {
             id = Long.parseLong(request.getParameter("id"));
             Movies movie = DBManager.getOneMovie(id);
             if (movie!=null) {
                 ArrayList<Seances> seances = DBManager.getAllSeancesByMovie(id);
                 if (seances != null) {
                     request.setAttribute("seances", seances);
                     request.setAttribute("movie", movie);
                     request.getRequestDispatcher("seances.jsp").forward(request,response);
                 }
             }
         }catch (Exception e) {

         }
    }
}
