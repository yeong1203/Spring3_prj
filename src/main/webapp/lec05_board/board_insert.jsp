<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/board_insert_rest" id="board_ins">
	제목 : <input type="text" name="title" id="title"><br>
	내용 : <textarea name="contents" id="contents" ></textarea><br>
	<input type="button" id="" value="글쓰기" >
</form>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script> 
$(function() {
	// 글 등록
	$("#title").click(  function(){
		//var formData = $("#board_ins").serialize();
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/board_insert_rest",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			//data : "seq="+$("seq").val()+"&reply="+$("#reply").val(),/*  */
			data: "title="+title+"&contexts="+contexts,
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  										console.log(myval);			
	  			}
		});	
	});	
}); 
</script>

</body>
</html>