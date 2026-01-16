package com.student.servlet;

import com.student.dao.StudentDAO;
import com.student.model.Student;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("Insert".equals(action)) {
            StudentDAO.insert(
                req.getParameter("name"),
                req.getParameter("email")
            );
        }
        else if ("Delete".equals(action)) {
            StudentDAO.delete(
                Integer.parseInt(req.getParameter("id"))
            );
        }
        else if ("Update".equals(action)) {
            StudentDAO.update(
                Integer.parseInt(req.getParameter("id")),
                req.getParameter("name"),
                req.getParameter("email")
            );
        }

        List<Student> list = StudentDAO.getAll();
        req.setAttribute("studentList", list);
        req.getRequestDispatcher("view.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Student> list = StudentDAO.getAll();
        req.setAttribute("studentList", list);
        req.getRequestDispatcher("view.jsp").forward(req, resp);
    }
}
