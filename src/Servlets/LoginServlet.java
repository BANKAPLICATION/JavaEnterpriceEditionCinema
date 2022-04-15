package Servlets;

import Classes.DBManager;
import Classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = "login?error";
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = DBManager.getUserByEmail(email);
        if (user!=null && user.getEmail().equals(email)) {
            if (user.getPassword().equals(password)) {
                System.out.println(user.toString());
                HttpSession current_user = request.getSession();
                current_user.setAttribute("CURRENT_USER", user);
                String fullasd = user.getFull_name().replace(" ", "");
                Cookie cookie = new Cookie("full_name", fullasd);
                Cookie cookiep = new Cookie("password", user.getPassword());
                cookie.setMaxAge(3600);
                cookiep.setMaxAge(3600);
                response.addCookie((cookie));
                response.addCookie(cookiep);
                if (user.getRole().getName().equals("admin")) {
                    redirect = "/admin";
                }
                else {
                    redirect = "/home";
                }
            }else {
                redirect = "login?passworderror";
            }
        }else {
            redirect = "login?emailerror";
        }
        response.sendRedirect(redirect);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request,response);
    }
}
