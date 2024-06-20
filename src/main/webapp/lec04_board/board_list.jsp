<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${KEY_BOARDLIST}" var="bvo">
	${bvo.seq}</t> <a href="${pageContext.request.contextPath}/board_detail?seq=${bvo.seq}">${bvo.title}</a></t> ${bvo.regate} <br>
</c:forEach>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script> $(function() { }); </script>

</body>
</html>