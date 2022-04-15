package Servlets;

import Classes.DBManager;
import Classes.Seances;
import Classes.Ticket;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/buyticket")
public class BuyTicketServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
           if (current_user!=null) {
               Long id = 0L;
               try {
                   id = Long.parseLong(request.getParameter("id"));
                   System.out.println(id);
                   Seances seanc = DBManager.getOneSeanc(id);
                   if (seanc!=null) {
                       Ticket ticket = new Ticket();
                       ticket.setCount(Integer.parseInt(request.getParameter("count")));
                       ticket.setSeanc(seanc);
                       ticket.setUser(current_user);
                       if (DBManager.addTicket(ticket)) {
                           response.sendRedirect("/seances?id="+seanc.getMovie().getId()+"&success");
                       }else {

                       }
                   }
               } catch (Exception e) {

               }
           }else {
               response.sendRedirect("/login");
           }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
