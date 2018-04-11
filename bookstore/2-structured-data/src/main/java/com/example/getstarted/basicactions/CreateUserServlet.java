package com.example.getstarted.basicactions;

import com.example.getstarted.daos.UserDao;
import com.example.getstarted.objects.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
// [START annotations]
@MultipartConfig
@WebServlet(name = "register", urlPatterns = {"/register"})
// [END annotations]
public class CreateUserServlet extends HttpServlet {

  // [START setup]
  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    req.setAttribute("action", "AddUser");          // Part of the Header in createUserform.jsp
    req.setAttribute("destination", "register");  // The urlPattern to invoke (this Servlet)
    req.setAttribute("page", "registerform");           // Tells base.jsp to include createUserform.jsp
    req.getRequestDispatcher("/base.jsp").forward(req, resp);
  }
  // [END setup]

  // [START formpost]
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
      IOException {
    UserDao dao = (UserDao) this.getServletContext().getAttribute("dao");
// [START bookBuilder]
    User user = new User.Builder()
        .userName(req.getParameter("username"))
        .password(req.getParameter("password"))
        // .valid(Long.decode(req.getParameter("valid")))
        .build();
// [END bookBuilder]
    try {
      Long id = dao.createUser(user);
      // resp.sendRedirect("/user?id=" + id.toString());   // read what we just wrote
      resp.sendRedirect("/");
    } catch (Exception e) {
      throw new ServletException("Error creating user", e);
    }
  }
  // [END formpost]
}
// [END example]
