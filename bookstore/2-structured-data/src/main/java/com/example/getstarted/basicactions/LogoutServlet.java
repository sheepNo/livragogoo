package com.example.getstarted.basicactions;

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
@WebServlet(name = "logout", urlPatterns = {"/logout"})
// [END annotations]

public class LogoutServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, java.io.IOException {
        try {
            HttpSession session = req.getSession();
            session.invalidate();
        } catch (Exception e) {
            throw new ServletException("Error to logout", e);
        }
    }
}
