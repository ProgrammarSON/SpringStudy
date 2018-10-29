<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board/getBoard.jsp</title>
</head>
<body>

<%-- <c:forTokens items="${board.uploadFileName}" delims="||" var="img">
<img src="./images/${img}">
</c:forTokens> --%>

<table border="1" style="width: 500px;">

	<tr><td>번호</td><td>제목</td><td>작성자</td><td>내용</td><td>작성날짜</td><td>조회수</td></tr>	
	<c:forEach items="${list}" var="board">
		<tr>
			<td><a href="">${board.seq}</a></td><td>${board.title}</td><td>${board.writer}</td><td>${board.content}</td>
			<td>${board.regdate}</td><td>${board.cnt}</td> <td><a href="./FileDown.do?atchFileId=${board.uploadFileName}">${board.uploadFileName}</a></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>