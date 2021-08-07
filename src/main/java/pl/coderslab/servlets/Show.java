package pl.coderslab.servlets;

import pl.coderslab.User;
import pl.coderslab.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Show", value = "/show")
public class Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            long id=Long.parseLong(request.getParameter("id"));
            User user=UserDao.readUser(id);
            request.setAttribute("user",user);
            request.getRequestDispatcher("/show.jsp").forward(request,response);
        }catch(NumberFormatException ex){
            // some code
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
