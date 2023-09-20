<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>
		<c:out value="${exception.getMessage()}"></c:out>
	</h4>


	<!-- exception check를 위한 코드 (개발자를 위한 코드-실제 배포시에는 삭제) -->
	<ul>
		<c:forEach items="${exception.getStackTrace() }" var="stack">
			<li><c:out value="${stack }"></c:out></li>
		</c:forEach>
	</ul>

</body>
</html>