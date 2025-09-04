<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
</head>
<body>
	<h2>Tạo tài khoản mới</h2>
	<form action="register" method="post">
		<input type="text" name="username" placeholder="Tài khoản" /> <input
			type="password" name="password" placeholder="Mật khẩu" /> <input
			type="password" name="confirm" placeholder="Nhập lại mật khẩu" />
		<button class="btn btn-primary" type="submit">Tạo tài khoản</button>
	</form>
	<c:if test="${alert != null}">
		<p style="color: red">${alert}</p>
	</c:if>
</body>
</html>
