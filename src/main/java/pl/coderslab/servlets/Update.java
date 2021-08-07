package pl.coderslab.servlets;

import pl.coderslab.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Update", value = "/update")
public class Update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if(request.getParameter("username")==null||request.getParameter("username").isBlank()){
            request.setAttribute("id", request.getParameter("id"));
           request.getRequestDispatcher("/update.jsp").forward(request, response);

       }else {
           String id=request.getParameter("id");
           String username=request.getParameter("username");
           String email=request.getParameter("email");
           UserDao.update(id,username,email);
           response.sendRedirect("/user");

       }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
