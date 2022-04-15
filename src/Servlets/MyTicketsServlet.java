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
import java.util.ArrayList;

@WebServlet(value = "/mytickets")
public class MyTicketsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null) {
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getOneUser(id);
                if (user!=null) {
                    ArrayList<Ticket> tickets = DBManager.getTicketsByUser(user);
                    request.setAttribute("tickets", tickets);
                    request.getRequestDispatcher("/tickets.jsp").forward(request,response);
                }
            }catch (Exception e) {

            }
        }
    }
}
