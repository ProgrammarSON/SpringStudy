<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>insertUser.jsp</title>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userDTO" staticJavascript="false" xhtml="true" cdata="false" />
</head>
<body>
<h3>회원정보 등록</h3>
<form:form action="./insertUser.do" commandName="userDTO"
		   onsubmit="return validateUserDTO(this)">	<!-- userRegist.xml에서 UserDTO로 설정함 -->
	id <form:input path="id" /><br>
	pw <form:input path="password" /><br>
	name <form:input path="name"/><br>
	<%-- role <input type="text" name="role" value="${user.role}"><br> --%>
	role <form:select path="role">
			<form:options items="${roleMap}"/>		
		</form:select><br>
	<input type="submit" value="삽입" />
</form:form>

</body>
</html>