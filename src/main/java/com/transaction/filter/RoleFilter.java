package com.transaction.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RoleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please login first");
            return;
        }

        String role = (String) session.getAttribute("role");
        String path = req.getRequestURI();

        if (path.contains("/admin") && !"ADMIN".equals(role)) {

            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }

        chain.doFilter(request, response);
    }
}