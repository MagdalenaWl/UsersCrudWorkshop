package pl.coderslab.servlets;

import pl.coderslab.User;
import pl.coderslab.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Create", value = "/create")
public class Create extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("username")==null||request.getParameter("username").isBlank()){
            request.setAttribute("id", request.getParameter("id"));
            request.getRequestDispatcher("/create.jsp").forward(request, response);

        }else {
            String username=request.getParameter("username");
            String email=request.getParameter("email");
            String password=request.getParameter("password");
            User user = new User(username, email, password);
            UserDao.createUser(user);
            response.sendRedirect("/user");
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
