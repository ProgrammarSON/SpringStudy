<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user/getUser.jsp</title>
<script>
	function go_page(p){
		document.frm.page.value=p;
		document.frm.submit();
	}
</script>
</head>
<body>
<a href="insertUserForm.do">회원등록</a>
<div>
<%-- <c:if test="${userSearchDTO.searchKeyword == 'id'}"> <%out.print("selected='selected'"); %></c:if> --%>

<form action="getUsers.do" name="frm">
	<input type="hidden" name="page" value="1">
	<select name="searchCondition">
		<option value="id">아이디</option>
		<option value="name">이름</option>
	</select>
	<script>
		document.frm.searchCondition.value='${userSearchDTO.searchCondition}';
	</script>
	<input type="text" name="searchKeyword" value="${userSearchDTO.searchKeyword}">
	<input type="submit" value="검색">
</form>
</div>
<table border="1" style="width: 500px;">
	<tr><td>아이디</td><td>이름</td><td>패스워드</td><td>롤</td></tr>	
	<c:forEach items="${list}" var="user">
		<tr><td><a href="updateUsersForm.do?id=${user.id}">${user.id}</a></td><td>${user.name}</td><td>${user.password}</td><td>${user.role}</td></tr>
	</c:forEach>
</table>
<my:paging paging="${paging}" jsFunc="go_page"/>
</body>
</html>