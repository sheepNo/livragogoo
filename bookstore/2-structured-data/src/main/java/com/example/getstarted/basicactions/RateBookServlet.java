package com.example.getstarted.basicactions;

import com.example.getstarted.daos.BookDao;
import com.example.getstarted.objects.Book;
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

import java.util.Arrays;

// [START example]
@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(name = "rate", value = "/rate")
public class RateBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
        try {
            Book book = dao.readBook(Long.decode(req.getParameter("id")));
            req.setAttribute("book", book);
            req.setAttribute("action", "Rate");
            req.setAttribute("destination", "rate");
            req.setAttribute("page", "rateform");
            req.getRequestDispatcher("/base.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error loading book for rating", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
        HttpSession session = req.getSession(true);
        User user = (User) session.getAttribute("currentSessionUser");
        if (user == null) {
            throw new ServletException("You can't rate if you are not login.");
        }
        UserDao userDao = (UserDao) this.getServletContext().getAttribute("dao");
        try {
            if (userDao.alreadyRated(user.getId(), Long.decode(req.getParameter("id")))){
                throw new ServletException("You already rated this book.");
            }
            Book book = new Book.Builder()
            .author(req.getParameter("author"))
            .description(req.getParameter("description"))
            .publishedDate(req.getParameter("publishedDate"))
            .title(req.getParameter("title"))
            .id(Long.decode(req.getParameter("id")))
            .rating(Double.parseDouble(req.getParameter("rating")))
            .bufRating(Double.parseDouble(req.getParameter("bufRating")))
            .numberVotes(Double.parseDouble(req.getParameter("numberVotes")))
            .comments(req.getParameter("comments"))
            .imageUrl(req.getParameter("imageUrl"))
            .build();
            dao.rateBook(book);
            User newUser = new User.Builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .myList(user.getMyList())
                .rated(req.getParameter("id") + ":" + req.getParameter("bufRating") + "%µ" + user.getRated())
                .build();
            userDao.updateUser(newUser);
            resp.sendRedirect("/read?id=" + req.getParameter("id"));
        } catch (Exception e) {
            throw new ServletException("Error rating book", e);
        }
    }
}
// [END example]
