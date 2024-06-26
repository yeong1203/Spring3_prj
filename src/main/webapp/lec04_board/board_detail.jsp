<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.lec04.di.board.BoardVO" %>
<%@ page import="com.lec04.di.board.BoardDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.lec04.di.board.ReplyVO" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Tables - SB Admin</title>
    </head>
<body>
<!-- --------------------------------------- 게시물 상세보기 ------------------------------- -->
<%
	/* BoardVO bvo = (BoardVO)request.getAttribute("KEY_BOARDDETAIL"); */
%>
<form id="boardForm" action="/board_detail?seq=${KEY_BOARDDETAIL.seq}" method="get">

	<input type="hidden" name="seq" value="${KEY_BOARDDETAIL.seq}">
	<input type="hidden" name="regid" value="${KEY_BOARDDETAIL.regid}">
	
	<table border="1" width="100%">
	<tr>
	   <th width="20%">글번호</th>
	   <td width="80%">${KEY_BOARDDETAIL.seq}</td>
	</tr>
	<tr>
	      <th>작성자</th>
	      <td>${KEY_BOARDDETAIL.regid}</td>
	</tr>
	<tr>
	      <th>작성일</th>
	      <td><input type="text" name="regdate" value="${KEY_BOARDDETAIL.regate}" readonly></td>
	</tr>
	<tr>
	      <th>제목</th>
	      <td><input type="text" name="title" size=60  value="${KEY_BOARDDETAIL.title}"></td>
	</tr>
	<tr>
	      <th>내용</th>
	      <td><textarea name="contents" cols="80" rows="6">${KEY_BOARDDETAIL.contents}</textarea></td>
	</tr>
	<tr>
		<td colspan=2 align="center">				
			<input type="button" id="uptButton" value="수정">
			<input type="button" id="delButton" value="삭제" >
			<input type="button" id="listButton" value="리스트">
		</td>
	</tr>
	</table>

</form>
<!-- --------------------------------------- 댓글목록 ------------------------------- -->
<br>
<c:if test="${fn:length(KEY_BOARDDETAIL.replies) > 0 }" >
<table width=100% border=1>
<c:forEach items="${KEY_BOARDDETAIL.replies}" var="rvo">
	<tr><td><font color=red><a href="${pageContext.request.contextPath}/reply_delete?seq=${KEY_BOARDDETAIL.seq}&rseq=${rvo.rseq}">[X]</a></font>${rvo.reply}</td></tr>
</c:forEach>
</table>
</c:if>

<!-- --------------------------------------- 댓글등록 ------------------------------- -->
<br>
<form method="post" action="${pageContext.request.contextPath}/reply_insert">
<input type=hidden name=seq value="${KEY_BOARDDETAIL.seq}">
<table width=100% border=1>
<tr>
	<td>
		<input type="text" size=100 name="reply">
		<input type="submit" value="댓글등록">
	</td>
</tr>
</table>
</form>


<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
		$(function() {
			//---------------------------------------------------------
			// <form> 제어하기
			//---------------------------------------------------------
			//$(".btn.btn-primary.btn-block").click()~~
			
			$("#uptButton").click(  function(){
				alert("수정");
				$("#boardForm").attr("method","post");
				$("#boardForm").attr("action","${pageContext.request.contextPath}/board_update");				
				$("#boardForm").submit();
				return true;
			});
			$("#delButton").click(  function(){
				alert("삭제");
				$("#boardForm").attr("method","post");
				$("#boardForm").attr("action","${pageContext.request.contextPath}/board_delete");
				$("#boardForm").submit();
				return true;
			});
			$("#listButton").click(  function(){
				location.href = "${pageContext.request.contextPath}/board_list";
			});            
			
			//------------------------------------------------------------
			
		});
		
	</script>
</body>
</html>


