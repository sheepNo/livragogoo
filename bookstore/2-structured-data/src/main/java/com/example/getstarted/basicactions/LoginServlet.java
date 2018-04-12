package com.example.getstarted.basicactions;

import com.example.getstarted.daos.UserDao;
import com.example.getstarted.objects.User;

import java.io.IOException;
import java.util.List;

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
            User userToLogin = null;
            String userName = req.getParameter("username");
            List<User> listUsers = dao.listUsers();
            for (User userStored: listUsers) {
                if (userStored.getUserName().equals(userName)) {
                    userToLogin = userStored;
                }
            }
            if (userToLogin == null) {
                throw new ServletException("You are not registered.");
            }
            User user = new User.Builder()
            .id(userToLogin.getId())
            .userName(userName)
            .password(req.getParameter("password"))
            .myList(userToLogin.getMyList())
            .rated(userToLogin.getRated())
            // .valid(Long.decode(req.getParameter("valid")))
            .build();
            if (dao.login(user)) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentSessionUser", user);
                resp.sendRedirect("/mylist?userid=" + user.getId().toString());
            } else {
                resp.sendRedirect("/login");
                // throw new ServletException("Wrong password.");
            }
        } catch (Exception e) {
            resp.sendRedirect("/login");
            // throw new ServletException("Error to login", e);
        }
    }
}
