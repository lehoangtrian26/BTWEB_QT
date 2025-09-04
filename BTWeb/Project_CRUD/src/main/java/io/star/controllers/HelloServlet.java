package io.star.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/home", "/trangchu" })
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//Get hiển thị tham số, Post bảo mật tham số
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ten");

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Xin chào " + (name != null ? name : "khách") + "</h1>");
        out.println("<form action='logout' method='get'>");
        out.println("<button type='submit'>Logout</button>");
        out.println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp); // tái sử dụng
    }
}
