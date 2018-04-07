package com.example.getstarted.basicactions;

import com.example.getstarted.daos.BookDao;
import com.example.getstarted.objects.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(name = "rate", value = "/rate")
public class RateBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        // Long id = Long.decode(req.getParameter("id"));
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
        try {
            Book book = new Book.Builder()
            .id(Long.decode(req.getParameter("id")))
            .rating(req.getParameter("rating"))
            .build();
            dao.rateBook(book);
            resp.sendRedirect("/books");
        } catch (Exception e) {
            throw new ServletException("Error deleting book", e);
        }
    }
}
// [END example]