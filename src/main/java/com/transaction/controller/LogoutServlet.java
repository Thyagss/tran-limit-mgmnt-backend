package com.transaction.controller;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogoutServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setContentType("text/plain");
        resp.getWriter().write("Logged Out Successfully");
        logger.info("Logout completed successfully");
    }
}
