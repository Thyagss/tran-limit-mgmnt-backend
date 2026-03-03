package com.transaction.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        // false → don't create new session if none exists

        if (session != null) {
            session.invalidate();  // destroys session
        }

        resp.setContentType("text/plain");
        resp.getWriter().write("Logged Out Successfully");
    }
}
