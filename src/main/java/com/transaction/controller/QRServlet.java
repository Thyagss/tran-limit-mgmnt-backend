package com.transaction.controller;

import com.transaction.util.QRUtil;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class QRServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String txnId = req.getParameter("txnId");

        if (txnId == null) {
            resp.setStatus(400);
            resp.getWriter().write("txnId is required");
            return;
        }

        resp.setContentType("image/png");

        String qrText = "TXN:" + txnId;

        try {
            QRUtil.generateQRCode(qrText, resp.getOutputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}