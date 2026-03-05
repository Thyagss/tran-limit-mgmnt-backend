package com.transaction.controller;

import com.transaction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        boolean success = userService.registerUser(name, username, email, mobile, hashedPassword);

        response.setContentType("text/plain");

        if (success) {
            response.getWriter().write("User Registered Successfully");
        } else {
            response.getWriter().write("Registration Failed");
        }
    }
}