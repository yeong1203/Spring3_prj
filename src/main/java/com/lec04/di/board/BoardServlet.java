//package com.lec04.di.board;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/BoardServlet")
//public class BoardServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public BoardServlet() { }
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	    request.setCharacterEncoding("UTF-8");
//	    response.setContentType("text/html;charset=UTF-8");
//	    BoardDAO dao = new BoardDAO();
//	    
//	    String pagecode = request.getParameter("pagecode");
//	    // 게시물 목록보기
//	    if(pagecode.equals("B001")) {
//		    ArrayList<BoardVO> list = dao.boardSelect();
//		    
//		    request.setAttribute("KEY_BOARDLIST", list); // 어떤 객체를 담을 것인가.
//		    RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");	// (링크를 패치해서), 객체를 jsp에 연결다리를 준다.
//		    // select를 forwad 되어서 오는 것이기 때문에 포워딩 문제가 없다.
//		    rd.forward(request, response);	// toGet 하고 있는 애를 forward로 두가지 키=벨류 전부 담아서 보내진다.
//		    // 보낼 객체를 forward 해서 보내는 것.
//		    // request.getRequestDispatcher("tables.jsp").forward(request, response); 이것과 동일한 코드
//		    // 서블릿에서 데이터와 jsp를 다 넘겨라! 하는 것.
//		    // 넘겨질 객체가 있을 때 forward 사용.
//
//    	// 게시물 상세보기
//	    } else if(pagecode.equals("B002")) {
//	    	// wrapper?
//	    	int seq = Integer.parseInt(request.getParameter("seq"));	// seq가 와야 selectOne 할 수 있음! 이게 제일 중요.
//	    	
////	    	BoardVO bvo = dao.boardSelectOne(seq);
////	    	request.setAttribute("KEY_BOARDVO", bvo);	    	
////	    	
////	    	List<ReplyVO> rlist = dao.replySelect(seq);
////	    	request.setAttribute("KEY_REPLYLISST", rlist);
//	    	
//	    	// join 
//	    	BoardVO bvo = dao.boardReplySelect(seq);
//	    	request.setAttribute("KEY_BOARDVO", bvo);
//	    	
////	    	request.setAttribute("KEY_BOARDVO", bvo);
////	    	System.out.println(rvo);
////	    	request.setAttribute("KEY_REPLYVO", rvo);
//	    	RequestDispatcher rd = request.getRequestDispatcher("tables_detail.jsp");
//	    	rd.forward(request, response);	    	
//	    	
//	    	
//	    	System.out.println();
//	    } else if(pagecode.equals("B003")) {
//	    	// B003 수정버튼
//	    	
//	    	System.out.println("===== 수정 =====" );
//
//    	// B005 댓글 삭제
//	    } else if(pagecode.equals("B005")) {
//	    	int seq = Integer.parseInt(request.getParameter("seq")) ;
//	    	int rseq = Integer.parseInt(request.getParameter("rseq")) ;
//	    	int deleteRows = dao.replyDelete(rseq);
//	    	// 상세보기 파라미터 : pagecode: B002, seq
//	    	response.sendRedirect(request.getContextPath()+"/BoardServlet?pagecode=B002&seq="+seq);	//상세로 넘어감.
//	    
//	    } else {
//	    	response.sendRedirect("500.html");
//	    	// response.sendRedirect : 단순 페이지 이동.
//	    }
//	    
////	    
////	    // board 목록 DB에서 꺼내기
////	    BoardDAO dao = new BoardDAO();
////	    ArrayList<BoardVO> list = dao.boardSelect();
//////	    결과를 가진 list를 tables 에 이제 넘겨줘야한다.
////	    
//////	     response.sendRedirect("xx.jsp") : 글자 전송에 사용
//////	    get방식으로 넘기는 방법은 문자열만 가능. 
//////	    response.sendRedirect("table.jsp?UID=kim");
////	    
//////	    객체 전송에 사용.
//////	    request.setAttribute("", list);		// 요청자에게 ("무슨키", 무슨값)
////	    /**
////	     * <글자 전송에 사용하는 것. >
////	     *  - response.sendRedirect("xx.jsp");
////	     *  - response.sendRedirect("table.jsp?UID=kim");
////	     * 
////	     * <객체 전송에 사용.>
////	     * - 세션에 담아버리면 어딜가나 다 따라다님.
////	     * - page scrop : 가져온 값을 어느 범위까지 공유해서 사용할 것 인가?
////	     * - page < request < session < application
////	     * - request.getAttribute();   // 
////	     * 
////	    // application도 내장 객체. -- 톰캣 연결된 프로젝트들과 쉐어링할때 쓰는 것. 가장 크다!
////	    // jsp 안에서만 쓰는게 page  
////	    // 해당 jsp 요청한 서블릿까지 = request		    // 요청-> 포워딩 하면 끝.	 서블릿까지.!   
////	    // 해당 jsp 뿐만아니라 모든 페이지 = session
////	    // 해당 jsp 뿐만 아니라 모든 프로젝트까지 = application
////	     * 
//////	    HttpSession session = request.getSession();
//////	    session.setAttribute("KEY_SESS_USERID", list);
////	        	    	    
////	    //객체 전송을 위한 jsp 파일을 넘긴다.
////	     */
////	    request.setAttribute("KEY_BOARDLIST", list);
////	    RequestDispatcher rd = request.getRequestDispatcher("tables.jsp");	// (링크를 패치해서), 객체를 jsp에 연결다리를 준다.
////	    rd.forward(request, response);	// toGet 하고 있는 애를 forward로 두가지 키=벨류 전부 담아서 보내진다.
////	    // 객체를 가지고 가면 forward, 그냥 가면 send... 두개 다 열면 에러남.
//		System.out.println("====request method : GET ====");
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    request.setCharacterEncoding("UTF-8");
//	    response.setContentType("text/html;charset=UTF-8");
//
//	    String pagecode = request.getParameter("pagecode");
//	    BoardDAO dao = new BoardDAO();
////	    System.out.println(pagecode);
//	    
//	    // 게시물 수정
//	    if(pagecode.equals("B003")) {
//	    	// B003 수정버튼
//	    	// 사용자에게서 받을 수 있는 값들만 들어온다.
//	    	// 즉, input의 값만 온다. 
//	    	// ex. seq=1&regid=kim&... form태그에 있는 애들은 이렇게 들어온다.
//	    	// 한 줄로 작성되어서 post는 body에 저 모양으로 붙어서 온다.
//	    	// get방식이면 header에 붙어서 쿼리스트링으로 날아온다.
//	    	// input에 있는 name값, 쿼리스트링으로 오는 값 => getParameter로 꺼낼 수 있다!!
//	    	ArrayList<BoardVO> list = dao.boardSelect();
////	    	System.out.println(request.getParameter("seq"));
////	    	System.out.println(request.getParameter("regid"));
////	    	System.out.println(request.getParameter("regate"));
////	    	System.out.println(request.getParameter("title")); 
////	    	System.out.println(request.getParameter("contents"));
//	    	//---- 여기  sys.out 은 값 넘어오는지 체크 한 것.
//	    	BoardVO bvo = new BoardVO();
//	    	bvo.setSeq(Integer.parseInt(request.getParameter("seq")));
//	    	bvo.setTitle(request.getParameter("title"));
//	    	bvo.setContents(request.getParameter("contents"));	    	
//	    	int updateRows = dao.boardUpdate(bvo);
//	    	
//	    	// 수정이 되었다면 그 페이지에 머무르면 된다.
//	    	// 서블릿에서 어떻게 서블릿페이지로 넘길 것인가? 그 처리
//	    	// post로 들어와서 보드 서블릿으로 들어왔는데, 다시 상세보기 서블릿으로 들어와야하는 상황.
//		    // 수정하면 상세보기로 넘어와서 수정된 내용이 보여지면 됨.
//		    // <%=request.getContextPath()%>/BoardServlet?seq=<%=seq%>&pagecode=B002
//		    // request.getContextPath()+"/BoardServlet?seq="+seq+"pagecode=B002";
//		    if(updateRows >=1 ) {
//		    	response.sendRedirect(request.getContextPath()+"/BoardServlet?seq="+request.getParameter("seq")+"&pagecode=B002");
//		    } else {
//		    	response.sendRedirect("500.html");
//		    }
//	    	
//	    	System.out.println("===== 수정 =====" );
//
//    	// B004 게시글 삭제	    	
//	    } else if(pagecode.equals("B004")) {
//	    	System.out.println("===== 삭제 =====");
//	    	System.out.println(request.getParameter("seq"));
//	    	int seq = Integer.parseInt(request.getParameter("seq"));
//	    	int deleteRows = dao.boardDelete(seq);
//	    	System.out.println(deleteRows);
//	    	if( deleteRows >=1 ) {
//		    	response.sendRedirect(request.getContextPath()+"/BoardServlet?seq="+request.getParameter("seq")+"&pagecode=B001");
//		    } else {
//		    	response.sendRedirect("500.html");
//		    }
//	    // B006 댓글 등록
//	    } else if(pagecode.equals("B006")) {
//	    	System.out.println("===댓글 등록");
//	    	
//	    	int seq = Integer.parseInt(request.getParameter("seq")) ;
//	    	String reply = request.getParameter("reply");
//	    	ReplyVO rvo = new ReplyVO();
//	    	rvo.setSeq(seq);
//	    	rvo.setReply(reply);
//	    	int insertRows = dao.replyInsert(rvo);
//	    	if(insertRows >=1) {
//	    		response.sendRedirect(request.getContextPath()+"/BoardServlet?pagecode=B002&seq="+seq);	//상세로 넘어감.
//	    	} else {
//	    		response.sendRedirect("500.html");
//	    	}
//	    	
//	    } else {
//	    	response.sendRedirect("500.html");
//	    }
//	    // 수정, 삭제를 처리하면 포워딩 하면 안된다. 
//	    // 꼭 리다이렉트 처리 해야한다.
//	    
//	    
//		System.out.println("====request method : POST ====");
//	}
//	
//	
//
//}
