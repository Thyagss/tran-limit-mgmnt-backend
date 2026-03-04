package com.transaction.controller;

import com.transaction.model.User;
import com.transaction.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userService.loginUser(username, password);

        resp.setContentType("text/plain");

        if (user != null) {


            HttpSession session = req.getSession();
            session.setAttribute("customerId", user.getCustomerId());
            session.setAttribute("role", user.getRole());

            resp.getWriter().write("Login Successful");

        } else {
            resp.setStatus(401);
            resp.getWriter().write("Invalid Credentials");
        }
    }
}