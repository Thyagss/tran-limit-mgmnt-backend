package com.transaction.controller;

import com.transaction.model.User;
import com.transaction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        logger.info("Login attempt for username: {}", username);

        User user = userService.loginUser(username, password);
        resp.setContentType("text/plain");

        if (user != null) {

            HttpSession session = req.getSession();
            session.setAttribute("customerId", user.getCustomerId());
            session.setAttribute("role", user.getRole());

            resp.getWriter().write("Login Successful");
        }
        else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid Credentials");
        }
    }
}