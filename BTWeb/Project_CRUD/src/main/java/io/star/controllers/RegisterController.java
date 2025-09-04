package io.star.controllers;

import java.io.IOException;

import io.star.service.UserService;
import io.star.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String confirm = req.getParameter("confirm");

			// server-side validation
			if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
				req.setAttribute("alert", "Username và password là bắt buộc.");
				req.getRequestDispatcher("views/register.jsp").forward(req, resp);
				return;
			}
			if (!password.equals(confirm)) {
				req.setAttribute("alert", "Mật khẩu không khớp.");
				req.getRequestDispatcher("views/register.jsp").forward(req, resp);
				return;
			}
			// (thêm kiểm tra email regex, phone, độ dài mật khẩu...)

			boolean ok = userService.register(username, password, 0);
			if (ok) {
				// redirect tới login kèm thông báo thành công
				resp.sendRedirect(req.getContextPath() + "/login?registered=success");
			} else {
				req.setAttribute("alert", "Username hoặc email/phone đã tồn tại.");
				req.getRequestDispatcher("views/register.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("alert", "Lỗi hệ thống, vui lòng thử lại.");
			req.getRequestDispatcher("views/register.jsp").forward(req, resp);
		}
	}
}
