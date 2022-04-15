package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/editAdmin")
public class EditAdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User)request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null && current_user.getRole().getName().equals("admin")) {
            String redirect = "edit?error";
            String full_name = request.getParameter("full_name");
            String password = request.getParameter("password");
            String re_password = request.getParameter("re_password");
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getOneUser(id);
                if (password.equals(re_password)) {

                    user.setFull_name(full_name);
                    user.setPassword(password);

                    if (DBManager.editUser(user)) {
                        redirect = "/edit?success";
                    }
                } else {
                    redirect = "edit?passworderror";
                }
            }catch (Exception e){

            }
            response.sendRedirect(redirect);
        }else {
            response.sendRedirect("/login");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User current_user = (User) request.getSession().getAttribute("CURRENT_USER");
        if (current_user!=null && current_user.getRole().getName().equals("admin")) {
            Long id = 0L;
            try {
                id = Long.parseLong(request.getParameter("id"));
                User user = DBManager.getOneUser(id);
                if (user!=null) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/editAdmin.jsp").forward(request,response);
                }
            }catch (Exception e) {

            }
        }else {
            response.sendRedirect("/login");
        }
    }
}
