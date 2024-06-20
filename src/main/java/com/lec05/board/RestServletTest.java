package com.lec05.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lec05.board.BoardDAO;
import com.lec05.board.BoardVO;


public class RestServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public RestServletTest() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		// ========== str -- str =========================
		String ename= request.getParameter("ename");
		System.out.println(ename);
		PrintWriter out = response.getWriter();

		// =========== 응답 str : 객체
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.boardList();
		//객체 리스트[VO]      --> [ {"title"="aaa" , "regid"="kim"} ]
		Gson gson = new Gson();  // gson으로 객체 json처리시킴.
		String listString = gson.toJson(list); // 객체를 toJson으로 json처리된다.
		//스트링 리스트[JSON]  --> "  [ {"title":"aaa" , "regid":"kim"} ]  "
		System.out.println(listString);
		out.println(listString);
		
//		==== 응답 str : 단순 문자열
//		out.println("200 ok");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////		doGet(request, response);
//		// ################################################################################
//		// 2번째 테스트 JSON -> String
//		// ============ json -- str ===============================
//		String jsonStr = request.getReader().lines().collect(Collectors.joining());		// JSON 모양으로 만들어 주는 것! 
//		System.out.println(jsonStr + "," + jsonStr.getClass());
//		Gson gson = new Gson();
//		BoardVO bvo = gson.fromJson(jsonStr, BoardVO.class);
//		
//		// $.ajax   : JSON.stringify({"title":"aaaaatitle","regid":"hong"});
//		// jsonStr  : "   {"title":"aaaaatitle","regid":"hong"}   "
//		// bvo      : {"title":"aaaaatitle","regid":"hong"}
////		System.out.println(bvo.getTitle());
//		PrintWriter out = response.getWriter();
//
//		
////		=== 복잡하게 보내기
//		====== 응답 str : ArrayList<VO>
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.boardList();
//		String listString = gson.toJson(list);		
//		System.out.println(listString);
////		out.println(listString);
//		
////		===== 응답 str : VO
//		out.println(jsonStr);  // 단순VO 꺼내는거라서 VO로 보내고 VO로 받아야함.
//		
////		단순하게 보내기.
////		out.println("응답 OK");
		
		
//		// ################################################################################
//		// 3번째 테스트 String -> JSON
//		response.setContentType("application/json;charset=UTF-8");  // json 으로 보낼때 핵심!! 이게 있어야 json이 된다.
//		
//		String ename= request.getParameter("ename");
//		System.out.println(ename);
//		PrintWriter out = response.getWriter();
//		Gson gson = new Gson();
//		
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.boardList();
//		//객체 리스트[VO]      --> [ {"title"="aaa" , "regid"="kim"} ]
//		String listString = gson.toJson(list);		
//		//스트링 리스트[JSON]  --> "  [ {"title":"aaa" , "regid":"kim"} ]  "
//		System.out.println(listString);
//		out.println(listString);
		
		// ################################################################################
		// 4번째 테스트 JSON -> JSON
		response.setContentType("application/json;charset=UTF-8");  // json 으로 보낼때 핵심!! 이게 있어야 json이 된다.
		
		String jsonStr = request.getReader().lines().collect(Collectors.joining());		// JSON 모양으로 만들어 주는 것! 
		System.out.println(jsonStr + "," + jsonStr.getClass());
		Gson gson = new Gson();
		BoardVO bvo = gson.fromJson(jsonStr, BoardVO.class);
		System.out.println(bvo.getTitle());
		
		PrintWriter out = response.getWriter();
		
		// ==== 응답
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVO> list = dao.boardList();
		//객체 리스트[VO]      --> [ {"title"="aaa" , "regid"="kim"} ]
		String listString = gson.toJson(list);		
		//스트링 리스트[JSON]  --> "  [ {"title":"aaa" , "regid":"kim"} ]  "
		System.out.println(listString);
		out.println(listString);
		
	}

}
