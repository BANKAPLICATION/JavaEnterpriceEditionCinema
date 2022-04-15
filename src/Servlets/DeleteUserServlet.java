package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null && current_user.getRole().getName().equals("admin")) {
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getOneUser(id);
                if (user!=null) {
                    if (DBManager.deleteUser(user)) {
                        response.sendRedirect("/admin");
                    }
                }
            }catch (Exception e) {

            }
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
