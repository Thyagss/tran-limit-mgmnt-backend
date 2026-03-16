package com.transaction.controller;

import com.transaction.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AccountServlet.class);

    private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        int customerId = (int) session.getAttribute("customerId");

        logger.info("Creating account for id: {}", customerId);

        boolean created = accountService.createAccount(customerId);

        resp.setContentType("text/plain");

        if (created) {
            logger.info("Account successfully created for id: {}", customerId);
            resp.getWriter().write("Account Created Successfully");
        } else {
            logger.info("Account creation failed for id: {}", customerId);
            resp.setStatus(500);
            resp.getWriter().write("Account Creation Failed");
        }
    }
}