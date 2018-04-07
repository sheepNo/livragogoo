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
    BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
    try {
      Book book = dao.readBook(Long.decode(req.getParameter("id")));
      req.setAttribute("book", book);
      req.setAttribute("action", "Edit");
      req.setAttribute("destination", "update");
      req.setAttribute("page", "form");
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
// [START bookBuilder]
      Book book = new Book.Builder()
          .author(req.getParameter("author"))
          .description(req.getParameter("description"))
          .id(Long.decode(req.getParameter("id")))
          .publishedDate(req.getParameter("publishedDate"))
          .title(req.getParameter("title"))
          .build();
// [END bookBuilder]
      dao.updateBook(book);
      resp.sendRedirect("/read?id=" + req.getParameter("id"));
    } catch (Exception e) {
      throw new ServletException("Error updating book", e);
    }
  }
}
// [END example]
