package com.transaction.controller;

import com.transaction.service.TransactionService;
import com.transaction.dao.TransactionDAO;
import javax.servlet.http.*;
import java.io.IOException;

public class TransactionServlet extends HttpServlet {

    private TransactionService service = new TransactionService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        int accountId = Integer.parseInt(req.getParameter("accountId"));
        double amount = Double.parseDouble(req.getParameter("amount"));

        boolean result = false;

        if ("deposit".equalsIgnoreCase(action)) {
            result = service.deposit(accountId, amount);
        } else if ("withdraw".equalsIgnoreCase(action)) {
            result = service.withdraw(accountId, amount);
        }

        if (result) {
            resp.getWriter().write("Success");
        } else {
            resp.getWriter().write("Failed");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String action = req.getParameter("action");

        if ("history".equalsIgnoreCase(action)) {

            int accountId = Integer.parseInt(req.getParameter("accountId"));

            var list = new TransactionDAO().getTransactions(accountId);

            resp.setContentType("text/plain");

            for (var txn : list) {
                resp.getWriter().write(
                        txn.getTxnId() + " | " +
                                txn.getTxnType() + " | " +
                                txn.getAmount() + " | " +
                                txn.getTxnDate() + "\n"
                );
            }

        } else {
            resp.getWriter().write("Invalid action");
        }
    }
}