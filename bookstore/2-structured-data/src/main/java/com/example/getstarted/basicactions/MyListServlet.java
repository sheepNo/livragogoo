package com.example.getstarted.basicactions;

import com.example.getstarted.daos.BookDao;
import com.example.getstarted.daos.DatastoreDao;
import com.example.getstarted.objects.Book;
import com.example.getstarted.objects.Result;

import java.io.IOException;
import java.util.List;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
// a url pattern of "" makes this servlet the root servlet
@WebServlet(name = "mylist", urlPatterns = {"", "/mylist"}, loadOnStartup = 1)
@SuppressWarnings("serial")
public class MyListServlet extends HttpServlet {

  @Override
  public void init(){
    BookDao dao = null;

    // Creates the DAO based on the Context Parameters
    String storageType = this.getServletContext().getInitParameter("bookshelf.storageType");
    switch (storageType) {
      case "datastore":
        dao = new DatastoreDao();
        break;
      default:
        throw new IllegalStateException(
            "Invalid storage type. Check if bookshelf.storageType property is set.");
    }
    this.getServletContext().setAttribute("dao", dao);
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,
      ServletException {
    BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
    String startCursor = req.getParameter("cursor");
    // List<Book> books = null;
    String[] myBooksID = user.getMyList().split("%µ");
    List<String> myBooks = new LinkedList<String> ();
    for (String id: myBooksID){
        if (!string.equals("")){
            Book book = dao.readBook(id);
            myBooks.add(book);
        }
    }
    String endCursor = null;
    try {
      // Result<Book> result = dao.listBooks(startCursor);
      // books = result.result;
      Collections.sort(myBooks, (a,b) -> a.getTitle().compareTo(b.getTitle()));
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
    req.setAttribute("page", "userlist");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
// [END example]
