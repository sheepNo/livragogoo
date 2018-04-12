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
// [START annotations]
@MultipartConfig
@WebServlet(name = "mylist", urlPatterns = {"/mylist"})
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
    Long userid = Long.decode(req.getParameter("userid"));
    BookDao dao = (BookDao) this.getServletContext().getAttribute("dao");
    // String startCursor = req.getParameter("cursor");
    // TODO better
    // HttpSession session = req.getSession(true);
    // User user = (User)session.getAttribute("currentSessionUser");
    // TODO or this way ?
    UserDao userdao = (UserDao) this.getServletContext().getAttribute("dao");
    User user = userdao.readUser(userid);

    String[] myBooksID = user.getMyList().split("%µ");
    List<Book> myBooks = new LinkedList<Book> ();
    for (String id: myBooksID){
        if (!id.equals("")){
            Book book = dao.readBook(Long.decode(id));
            myBooks.add(book);
        }
    }
    // String endCursor = null;
    try {
      // Result<Book> result = dao.listBooks(startCursor);
      // books = result.result;
      Collections.sort(myBooks, (a,b) -> a.getTitle().compareTo(b.getTitle()));
      // endCursor = result.cursor;
    } catch (Exception e) {
      throw new ServletException("Error listing books", e);
    }
    req.getSession().getServletContext().setAttribute("books", myBooks);
    StringBuilder bookNames = new StringBuilder();
    for (Book book : myBooks) {
      bookNames.append(book.getTitle() + " ");
    }
    // req.setAttribute("cursor", endCursor);
    // req.setAttribute("user", user);
    req.setAttribute("page", "userlist");
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
}
// [END example]
