package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null && current_user.getRole().getName().equals("admin")) {
            ArrayList<User> users = DBManager.getAllUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("admin.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }
    }
}
