<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
		<input type="text" name="username" placeholder="Tài khoản" /> <input
			type="password" name="password" placeholder="Mật khẩu" />
		<button type="submit">Đăng nhập</button>
	</form>
	<c:if test="${alert != null}">
		<p style="color: red">${alert}</p>
	</c:if>
</body>
</html>