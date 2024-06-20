package com.lec05.board;


import org.springframework.stereotype.Controller;  				// @Controller 얘꺼
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;	// @Autowired 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//public class EmpController2 extends MultiActionController {
// @Controller == <bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController" />

@Controller
public class BoardController {

	@Autowired 
	private BoardDAO boardDAO;
	
	@RequestMapping(value="/board_insert", method=RequestMethod.POST)
	public String ctlBoardInsert(@ModelAttribute BoardVO bvo) {	
		int insertRows = boardDAO.boardInsert(bvo);
		return "redirect:/board_list";		// insert 처리 후 board 리스트로 이동해서 board_list에서 처리.		 
	}
	
	// REST 진행중
//	@RequestMapping(value="/board_insert_rest", method=RequestMethod.POST)
//	@ResponseBody
//	public String ctlBoardInsertForRest(Model model, @ModelAttribute BoardVO bvo) {
//		int res = boardDAO.boardInsert(bvo);
//		return String.valueOf(res);  // res 값 전송. 
//	}
	
	
	@RequestMapping(value="/board_list")
	public String ctlBoardList(Model model) {	    // ArrayList<BoardVO>
		ArrayList<BoardVO> list = boardDAO.boardSelect();
		model.addAttribute("KEY_BOARDLIST", list);
		return "lec05_board/board_list";
	}

	
	
	@RequestMapping(value="/board_detail")
	public String ctlBoardDetail(Model model, @RequestParam("seq") int seq) {  // @ModelAttribute BoardVO bvo 는 seq 하나 받기에 너무 큼
		BoardVO bvo = boardDAO.boardSelectOne(seq);
		model.addAttribute("KEY_BOARDVO", bvo);

//		ReplyVO rlist = boardDAO.replySelect(seq);
//		model.addAttribute("KEY_REPLYLIST", rlist);
		/* 처리 방법 2가지 있음.
		 * 1. 최초 한번은 목록을 지우고 새로 불러옴.
		 * 2. 상세를 가지고와서 ajax를 가서 상세를 가지고옴. --- 이 부분처럼 작업함.
		 * */
//		BoardVO bvo = boardDAO.boardReplySelect(seq);
//		model.addAttribute("KEY_BOARDDETAIL", bvo);
		return "lec05_board/board_detail";
	}
	
	
	// 글 수정
	@RequestMapping(value="/board_update")
	public String ctlBoardUpdate(Model model, @ModelAttribute BoardVO bvo) {
		int updateRows = boardDAO.boardUpdate(bvo);
		return "redirect:/board_detail?seq="+bvo.getSeq();
	}
	
	// 글 삭제
	@RequestMapping(value="/board_delete")
	public String ctlBoardDelete(Model model, @RequestParam("seq") int seq) {
		int deleteRows = boardDAO.boardDelete(seq);
		return "redirect:/board_list";
	}
	
	
	// 댓글 목록 : REST
	@RequestMapping(value="/reply_list_rest", method=RequestMethod.POST,  produces = "application/json")
	@ResponseBody
	public ResponseEntity<ArrayList<ReplyVO>> ctlReplyListForRest(Model model
					, @RequestParam("seq") int seq) { //@RequestBody BoardVO bvo : 클라이언트로부터 JSON으로 들어올 때, 사용.	
//		System.out.println(seq);	
		ArrayList<ReplyVO> rlist = boardDAO.replySelect(seq);
		return new ResponseEntity<>(rlist, HttpStatus.OK); 			 
	}
	
	
	// 댓글 등록
	@RequestMapping(value="/reply_insert_rest", method=RequestMethod.POST)
	@ResponseBody
//	public ResponseEntity<String> ctlReplyInsertForRest(Model model,
	public String ctlReplyInsertForRest(Model model,
							@ModelAttribute ReplyVO rvo) {
		System.out.println(rvo.getReply());		
		int res = boardDAO.replyInsert(rvo);
		String msg = "입력에러";
		if(res > 0) {
			msg = "입력성공";
		}
		// return String.valueOf(res);  // res 값 전송. 	
		return String.valueOf(msg);
	}
		
	
	// 댓글 삭제 REST
	@RequestMapping(value="/reply_delete_rest")
	@ResponseBody
	public String ctlReplyDeleteForRest(Model model, @ModelAttribute ReplyVO rvo) {
		System.out.println(rvo.getRseq());
		int deleteRows = boardDAO.replyDelete(rvo.getRseq());
		String msg = "댓글삭제에러";
		if(deleteRows > 0) {
			msg = " 성공";
		}
		return String.valueOf(msg);
	}
	
	
	// 검색어 자동완성 = board_search
	@RequestMapping(value="/board_search", method=RequestMethod.POST,  produces = "application/json")
	@ResponseBody
	public ResponseEntity<ArrayList<BoardVO>> ctlSearchForRest(Model model
					, @RequestParam("search_str") String search_str) { //@RequestBody BoardVO bvo : 클라이언트로부터 JSON으로 들어올 때, 사용.	
		System.out.println(search_str);	
		ArrayList<BoardVO> rlist = boardDAO.boardList(search_str);
		return new ResponseEntity<>(rlist, HttpStatus.OK); 			 
	}
}

