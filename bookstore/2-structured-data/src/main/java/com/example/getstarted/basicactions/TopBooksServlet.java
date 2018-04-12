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

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
// a url pattern of "" makes this servlet the root servlet
@SuppressWarnings("serial")
@MultipartConfig
@WebServlet(name = "top", value = "/top")
// @WebServlet(name = "top", urlPatterns = {"", "/top"}, loadOnStartup = 1)
public class TopBooksServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
    String startCursor = req.getParameter("cursor");
    List<Book> books = null;
    String endCursor = null;
    try {
      Result<Book> result = dao.listBooks(startCursor);
      books = result.result;
      Collections.sort(books, (a,b) -> a.getRating() < b.getRating() ? 1 : a.getRating() == b.getRating() ? 0 : -1);
      endCursor = result.cursor;
    } catch (Exception e) {
      throw new ServletException("Error listing books", e);
    }
    req.getSession().getServletContext().setAttribute("books", books);
    StringBuilder bookNames = new StringBuilder();
    for (Book book : books) {
      bookNames.append(book.getTitle() + " ");
    }
    req.setAttribute("cursor", endCursor);
    req.setAttribute("page", "top");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
// [END example]
