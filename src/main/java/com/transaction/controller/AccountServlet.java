package com.transaction.controller;

import com.transaction.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        int customerId = (int) session.getAttribute("customerId");

        boolean created = accountService.createAccount(customerId);

        resp.setContentType("text/plain");

        if (created) {
            resp.getWriter().write("Account Created Successfully");
        } else {
            resp.setStatus(500);
            resp.getWriter().write("Account Creation Failed");
        }
    }
}