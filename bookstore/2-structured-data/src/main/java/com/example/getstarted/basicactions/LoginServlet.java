package com.example.getstarted.basicactions;

import com.example.getstarted.daos.UserDao;
import com.example.getstarted.objects.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

// [START example]
@SuppressWarnings("serial")
// [START annotations]
@MultipartConfig
@WebServlet(name = "login", urlPatterns = {"/login"})
// [END annotations]

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
        try {
            User user = dao.readUser(Long.decode(req.getParameter("id")));
            req.setAttribute("user", user);
            req.setAttribute("action", "Login");
            req.setAttribute("destination", "login");
            req.setAttribute("page", "loginform");
            req.getRequestDispatcher("/base.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error to login", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
        try {
            // [START bookBuilder]
            User user = new User.Builder()
            .id(Long.decode(req.getParameter("id")))
            .userName(req.getParameter("userName"))
            .password(req.getParameter("password"))
            .valid(Long.decode(req.getParameter("valid")))
            .build();
            // [END bookBuilder]
            if (dao.login(user)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentSessionUser", user);
                resp.sendRedirect("/user?id=" + req.getParameter("id"));
            } else {
                resp.sendRedirect("/invalidLogin");
            }
        } catch (Exception e) {
            throw new ServletException("Error to login", e);
        }
    }
}
