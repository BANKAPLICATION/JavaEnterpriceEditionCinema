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

@WebServlet(value = "/registr")
public class RegistrServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "registr?error";
        String full_name = request.getParameter("full_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        User user = DBManager.getUserByEmail(email);
        if (user == null) {
              if (password.equals(re_password)) {
                  User newUser = new User();
                  newUser.setEmail(email);
                  newUser.setFull_name(full_name);
                  newUser.setPassword(password);

                  newUser.setRole(new Roles(2L,"user"));
                    if (DBManager.addUser(newUser)) {
                           redirect = "/login";
                    }
              }else {
                  redirect = "registr?passworderror";
              }
        }else {
            redirect = "registr?emailerror";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/registr.jsp").forward(request,response);
    }
}
