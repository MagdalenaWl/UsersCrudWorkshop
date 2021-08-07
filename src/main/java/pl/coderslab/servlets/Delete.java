package pl.coderslab.servlets;

import pl.coderslab.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Delete", value = "/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            long id=Long.parseLong(request.getParameter("id"));
            UserDao.delete(id);
            response.sendRedirect("/user/list");
        }catch(NumberFormatException ex){
            // some code
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
