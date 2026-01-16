package com.example.servlet;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.example.dao.UserDao;
import com.example.model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("RegisterServlet invoked");


        User user = new User(name, email, password);
        int status = UserDao.save(user);

        if (status > 0) {
            response.sendRedirect("success.jsp");
        } else {
            response.getWriter().println("Error in registration");
        }
    }
}
