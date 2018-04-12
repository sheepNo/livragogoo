package com.example.getstarted.basicactions;

import com.example.getstarted.daos.BookDao;
import com.example.getstarted.daos.DatastoreDao;
import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.Result;
import com.example.getstarted.daos.UserDao;
import com.example.getstarted.objects.User;

import java.io.IOException;
import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


// [START example]
@SuppressWarnings("serial")
@WebServlet(name = "deleteallusers", value = "/deleteallusers")
public class DeleteAllUsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        HttpSession session = req.getSession();
        UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
        try {
            session.invalidate();

            List<User> listUsers = dao.listUsers();
            for (User user: listUsers) {
                dao.deleteUser(user.getId());
            }

            resp.sendRedirect("/books");

        } catch (Exception e) {
            throw new ServletException("Error deleting users", e);
        }
    }
}
// [END example]
