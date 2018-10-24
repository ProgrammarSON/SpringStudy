<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
</head>
<body>
userDTO : ${userDTO}<hr> 
user : ${user}<hr>
<h3>로그인</h3><%=application.getRealPath("/") %>
<form action="./login.do" method="post">
	id <input type="text" name="id" value="${user.id}"><br>
	pw <input type="text" name="password" value="${user.password}"><br>
	<input type="submit" value="로그인" />
</form>

</body>
</html>