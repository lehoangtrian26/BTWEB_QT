package io.star.controllers;

import java.io.IOException;

import io.star.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        // Nếu chưa login thì về lại trang login
        if (session == null || session.getAttribute("account") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy thông tin user từ session
        User user = (User) session.getAttribute("account");

        // Gửi username ra view nếu cần
        req.setAttribute("username", user.getUserName());

        // Điều hướng theo role
        switch (user.getRole()) {
            case 1: // Admin
                resp.sendRedirect(req.getContextPath() + "/admin/home");
                break;
            case 2: // Manager
                resp.sendRedirect(req.getContextPath() + "/manager/home");
                break;
            default: // User thường
                resp.sendRedirect(req.getContextPath() + "/home");
                break;
        }
    }
}

