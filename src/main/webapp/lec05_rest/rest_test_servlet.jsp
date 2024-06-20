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
REST Test<hr>
1. String -- String <br>
<input type="text" name="ename" id="ename">
<input type="button" id="AjaxBtn" value="str-str"><hr>

2. JSON -- String <br>
<input type="button" id="json-str" value="json-str"><hr>
<!-- JSON으로 내보내고 String으로 받는것.  -->

3. String -- JSON <br>
<input type="button" id="str-json" value="str-json"><hr>
<!-- JSON으로 내보내고 StringJSON으로 받는것.  -->

3. JSON -- JSON <br>
<input type="button" id="json-json" value="str-json"><hr>

<div id="resultDIV"></div>


<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$(function() { 	
	// =========== 공통 : 테이블 그리기
	function makeTable(myval) {		
		var htmlStr = "<table border=1 width=50%><tr><th>제목</th><th>글쓴이</th></tr>";
		$.map( myval, function( MYval, MYidx ) {
				htmlStr += "<tr><td>" +MYval["title"] + "</td><td>"+MYval["regid"] +"</td></tr>"
				//console.log(MYval["title"] + "," + MYval["regid"] + "," + MYidx);
		});
		htmlStr += "</table>";
		$("#resultDIV").empty();
		
		//$("#resultDIV").html(myval[0]["title"]);
		$("#resultDIV").html(htmlStr);
	}
	
	$("#AjaxBtn").click(function(){
 		var ename_val = $("#ename").val();		
		$.ajax({
			method: "GET",
			url : "${pageContext.request.contextPath}/RestServletTest",
			data : "ename="+ename_val,
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  										makeTable(myval);
	  			}
		});	
	});
	
	
	$("#json-str").click(  function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
		// objData는 내 컴퓨터 메모리에 있는 객체 정보. 그래서 내 메모리 정보를 줘야하기 때문에, application/json; & stringify로처리.
		$.ajax({
			method      : "POST",
	        url         : "${pageContext.request.contextPath}/RestServletTest",
	        contentType : "application/json; charset=UTF-8", 	// 진짜 JSON으로 보낼때, 필수
       		data 		: JSON.stringify(objData),	// 객체는 그냥 못나가서 stringify로 감싸야함. 없을 땐,  key=val(쿼리스트링으로 나감)== 내부적으로 쿼리스트링으로 컴버팅됨, 꼭 stringigy로 감싸줘야 JSON타입파일로 나가진다.  // 	"{MY_JSONKEY={ "name":"kim", "pw":"123" }}"
       		error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
						  			console.log("변환전 " + myval);
						  			myval= JSON.parse(myval);
						  			console.log("변환후 : "+ myval);
	  								
						  			makeTable(myval);  
									console.log(myval["title"]);
						}
		});
	});
	
	$("#str-json").click(function(){
 		var ename_val = $("#ename").val();		
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/RestServletTest",
			data : "ename="+ename_val,
			// dataType: "json",  // 받는 타입
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  						console.log(myval);
	  						makeTable(myval);
	  			}
		});	
	});
	
	
	$("#json-json").click(function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
 		//var ename_val = $("#ename").val();		
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/RestServletTest",
			data : JSON.stringify(objData),
			contentType : "application/json; charset=UTF-8",
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  						console.log(myval);
							makeTable(myval);
	  			}
		});	
	});
	
		
});

</script>

</body>
</html>