package Servlets;

import Classes.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(value = "/addSeance")
public class AddSeanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try {
           User current_user = (User) request.getSession().getAttribute("CURRENT_USER");
           if (current_user != null && current_user.getRole().getName().equals("moderator")) {
               System.out.println("kurrent kirdu");
               String movie_id = request.getParameter("movies");
               String cinema_id = request.getParameter("cinema");
               Movies movie = DBManager.getOneMovie(Long.parseLong(movie_id));
               Cinema cinema = DBManager.getOneCinema(Long.parseLong(cinema_id));
               if (movie!=null && cinema!=null) {
                   System.out.println("ekeundi prover ki kirdi");
                   String date = request.getParameter("date");
                   System.out.println(date);
                   Date dates = new SimpleDateFormat("yyyy-mm-dd").parse(date);
                   Seances seances = new Seances();
                   seances.setCinema(cinema);
                   seances.setMovie(movie);
                   System.out.println("dates tan otti");
                   Timestamp fromTS1 = new Timestamp(dates.getTime());
                   seances.setDate(fromTS1);
                     if (DBManager.addSeance(seances)) {
                         System.out.println("sdgsdag");
                        response.sendRedirect("/addSeance?success");
                     }else {
                         response.sendRedirect("/addSeance?error");
                     }
               }else {
                   response.sendRedirect("/login");
               }
           } else {
               response.sendRedirect("/login");
           }
       }catch (Exception e) {
        response.sendRedirect("/login");
       }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
            if (current_user!=null && current_user.getRole().getName().equals("moderator")) {

                ArrayList<Movies> movies = DBManager.getAllMovies();
                ArrayList<Cinema> cinemas = DBManager.getAllCinema();
                request.setAttribute("movies", movies);
                request.setAttribute("cinemas", cinemas);
                request.getRequestDispatcher("/addSeance.jsp").forward(request,response);
            }
            else {
                response.sendRedirect("/login");
            }
    }
}
