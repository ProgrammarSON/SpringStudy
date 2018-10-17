<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form>
	id <input type="text" name="id" value="${user.id}"><br>
	pw <input type="text" name="password" value="${user.password}"><br>
	name <input type="text" name="name" value="${user.name}"><br>
	role <input type="text" name="role" value="${user.role}"><br>
	<input type="submit" value="수정" />
</form>

</body>
</html>