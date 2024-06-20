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
SPRING REST Test : <input type="text" name="ename" id="ename"><hr>
<hr>

0.[RestController : @Controller + @ResponseBody ] String -- String<br>
<input type="button" id="rest-str-str" value="RestController str-str"><hr>

1. String -- String <br>
<input type="button" id="str-strBtn" value="str-str"><hr>

2. JSON -- String <br>
<input type="button" id="json-str" value="json-str"><hr>
<!-- JSON으로 내보내고 String으로 받는것.  -->

3. String -- JSON <br>
<input type="button" id="str-json" value="str-json"><hr>
<!-- JSON으로 내보내고 StringJSON으로 받는것.  -->

4. JSON -- JSON <br>
<input type="button" id="json-json" value="str-json"><hr>

5. 일반적형태(String) -- JSON <br>
<form id="regForm">
	<input type="text" id="title" name="title" >
	<input type="text" id="regid" name="regid" >
	<input type="button" id="writeButton" value="글쓰기"><hr>
</form>

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
	
	// ============== REST Controller 호출

	$("#rest_str_str").click(function () {
		  var ename = $("#ename").val();
		  $.ajax({
		    method: "POST",
		    url: "/restctl_str_str",
		    data: "ename=" + ename,
		    error: function (myval) {
		      console.log("에러 : " + myval);
		    },
		    success: function (myval) {
		      console.log("0.RestController 응답 : " + myval);
		    }
		  });
		});

	
	// =============== 일반 컨트롤러 호출 ================
	
	$("#str-strBtn").click(function () {
		  var ename = $("#ename").val();
		  $.ajax({
		    method: "POST",
		    url: "/ctl_str_str",
		    data: "ename=" + ename,
		    error: function (myval) {
		      console.log("에러 : " + myval);
		    },
		    success: function (myval) {
		      console.log("1.spring 응답 : " + myval);
		    }
		  });
		});
	
	
	$("#json-str").click(  function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
		// objData는 내 컴퓨터 메모리에 있는 객체 정보. 그래서 내 메모리 정보를 줘야하기 때문에, application/json; & stringify로처리.
		$.ajax({
			method      : "POST",
	        url         : "${pageContext.request.contextPath}/ctl_json_str",
	        contentType : "application/json; charset=UTF-8", 	// 진짜 JSON으로 보낼때, 필수!!
     		data 		: JSON.stringify(objData),	// 객체는 그냥 못나가서 stringify로 감싸야함. 없을 땐,  key=val(쿼리스트링으로 나감)== 내부적으로 쿼리스트링으로 컴버팅됨, 꼭 stringigy로 감싸줘야 JSON타입파일로 나가진다.  // 	"{MY_JSONKEY={ "name":"kim", "pw":"123" }}"
     		error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
			     			 console.log("2.spring 응답 : " + myval);
						}
		});
	});
	
	
	// 가장 많이 보내는 부분.
	$("#str-json").click(function(){
		var ename_val = $("#ename").val();		
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/ctl_str_json",
			data : "ename="+ename_val,
			// dataType: "json",  // 받는 타입
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval, textStatus, xhr){   // success에서는 내가 보낸 값 myval 빼고 textStatus, xhr 이 것도 가지고 올 수 있다.
	  						if(xhr.status == 200) {  // ok 사인을 이용해서 처리해 본 것.
		  						console.log("3.Spring 응답 : "+myval["message"]);	  							
	  						}
	  			}
		});	
	});
	
	// 응답은 보통 이것.
	// 클라이언트(json) -> 서버(json)
	$("#json-json").click(function(){
		var objData = {"title":"aaaaatitle","regid":"hong"};
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/ctl_json_json",
			data : JSON.stringify(objData),
			contentType : "application/json; charset=UTF-8",  // optional 아님. 필수
			error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  						console.log(myval);
	  			}
		});	
	});
	
	// 검색할 때, 아래 결과만 나오도록 활용할 수 있다.(where조건만 추가하면 활용가능)
	$("#writeButton").click(function(){
		var sendFormData = $("#regFrom").serialize();
		$.ajax({
			method: "POST",
			url : "${pageContext.request.contextPath}/ctl_normal",
			data : sendFormData,
			contentType : "application/json; charset=UTF-8",  // optional 아님. 필수
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