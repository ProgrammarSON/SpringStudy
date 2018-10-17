<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user/getUser.jsp</title>
</head>
<body>

<table border="1" style="width: 500px;">
	<tr><td>아이디</td><td>이름</td><td>패스워드</td><td>롤</td></tr>	
	<c:forEach items="${list}" var="user">
		<tr><td><a href="updateUsersForm.do?id=${user.id}">${user.id}</a></td><td>${user.name}</td><td>${user.password}</td><td>${user.role}</td></tr>
	</c:forEach>
</table>
</body>
</html>