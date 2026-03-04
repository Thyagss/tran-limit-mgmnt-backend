package com.transaction.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
        import java.io.IOException;


public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null &&
                session.getAttribute("customerId") != null);

        if (loggedIn) {

            chain.doFilter(request, response);
        } else {
            // Block request
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Please login first.");
        }
    }
}
