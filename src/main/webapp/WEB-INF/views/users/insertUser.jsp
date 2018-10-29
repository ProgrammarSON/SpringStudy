<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insertUser.jsp</title>
</head>
<body>
<form action="./insertUser.do">
	id <input type="text" name="id" value="${user.id}"><br>
	pw <input type="text" name="password" value="${user.password}"><br>
	name <input type="text" name="name" value="${user.name}"><br>
	<%-- role <input type="text" name="role" value="${user.role}"><br> --%>
	role <select name="role">
			<option value="">선택</option>
		<c:forEach items="${roleMap}" var="temp">
			<option value="${temp.key}">${temp.value}</option>
		</c:forEach>
	</select><br>
	<input type="submit" value="삽입" />
</form>

</body>
</html>