<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>
		<c:out value="${ exception.getMssage() }"></c:out>
	</h4>

	<ul>
		<c:forEach var="stack" items="${exception.getStackTrace()}">
			<li><c:out value="${stack}"></c:out></li>
		</c:forEach>
	</ul>
	<!-- error를 받는 page -->
</body>
</html>