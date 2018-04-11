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
@WebServlet(name = "comments", value = "/comments")
public class CommentsBookServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
        try {
            Book book = dao.readBook(Long.decode(req.getParameter("id")));
            req.setAttribute("book", book);
            req.setAttribute("action", "Comment");
            req.setAttribute("destination", "comments");
            req.setAttribute("page", "commentsform");
            req.getRequestDispatcher("/base.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error loading book for commenting", e);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
    IOException {
        BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
        try {
            Book book = new Book.Builder()
            .author(req.getParameter("author"))
            .description(req.getParameter("description"))
            .publishedDate(req.getParameter("publishedDate"))
            .title(req.getParameter("title"))
            .imageUrl(req.getParameter("imageUrl"))
            .id(Long.decode(req.getParameter("id")))
            .rating(Double.parseDouble(req.getParameter("rating")))
            //.bufRating(Double.parseDouble(req.getParameter("bufRating")))
            .numberVotes(Double.parseDouble(req.getParameter("numberVotes")))
            .comments(req.getParameter("comments"))
            .bufComments(req.getParameter("bufComments"))
            .build();
            dao.commentsBook(book);
            resp.sendRedirect("/read?id=" + req.getParameter("id"));
        } catch (Exception e) {
            throw new ServletException("Error commenting book", e);
        }
    }
}
// [END example]
