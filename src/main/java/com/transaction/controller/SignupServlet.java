package com.transaction.controller;

import com.transaction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignupServlet.class);

    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");

        logger.info("Registering new user with username: {}", username);
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