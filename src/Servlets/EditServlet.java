package Servlets;

import Classes.DBManager;
import Classes.Roles;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/edit")
public class EditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null) {
            String redirect = "edit?error";
            String full_name = request.getParameter("full_name");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");

                if (password.equals(re_password)) {

                    current_user.setFull_name(full_name);
                    current_user.setPassword(password);

                    if (DBManager.editUser(current_user)) {
                        redirect = "/edit?success";
                    }
                } else {
                    redirect = "edit?passworderror";
                }

            response.sendRedirect(redirect);
        }else {
            response.sendRedirect("/login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null) {
            request.getRequestDispatcher("edit.jsp").forward(request,response);
        }else {
            response.sendRedirect("/login");
        }

    }
}
