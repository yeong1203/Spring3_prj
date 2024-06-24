package com.lec08.dao;

import org.springframework.stereotype.Controller;  				// @Controller 얘꺼
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;	// @Autowired 
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestParam;

//public class EmpController2 extends MultiActionController {
// @Controller == <bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController" />

@Controller
public class BoardController {

	@Autowired 
	//private BoardDAO boardDAO;
	private BoardService boardService;  // 타입은부모_인터페이스ㅡ = new 자식타입(); === 다형성 
										// private BoardService BoardService = new 자식타입() ; // 자식들이 new 됨.

	
	@RequestMapping(value="/board_insert", method=RequestMethod.POST)
	public String ctlBoardInsert(@ModelAttribute BoardVO bvo) {	
		int insertRows = boardService.svcBoardInsert(bvo);
		return "redirect:/board_list";		 
	}
	
	@RequestMapping(value="/board_list")
	public String ctlBoardList(Model model) {	
		ArrayList<BoardVO> list = boardService.svcBoardList();
		model.addAttribute("KEY_BOARDLIST",list);
		return "/lec04_board/board_list";
	}
	
	@RequestMapping(value="/board_detail")
	public String ctlBoardDetail(Model model, @RequestParam("seq") int seq) {  // @ModelAttribute BoardVO bvo 는 seq 하나 받기에 너무 큼
		Map map = boardService.svcBoardDetail(seq);
		model.addAttribute("KEY_BOARDVO", (BoardVO)map.get("my_bvo"));
		model.addAttribute("KEY_REPLYLIST", (ArrayList<ReplyVO>)map.get("my_rlist"));
		return "lec04_board/board_detail";
	}
	
	
	@RequestMapping(value="/board_update", method=RequestMethod.POST)
	public String ctlBoardUpdate(Model model, @ModelAttribute BoardVO bvo) {
		int updateRows = boardService.svcBoardUpdate(bvo);
		return "redirect:/board_detail?seq="+bvo.getSeq();
	}
	
	@RequestMapping(value="/board_delete")
	public String ctlBoardDelete(Model model, @RequestParam("seq") int seq) {
		int deleteRows = boardService.svcBoardDelete(seq);
		return "redirect:/board_list";
	}
	
	@RequestMapping(value="/reply_insert",  method=RequestMethod.POST)
	public String ctlReplyInsert(Model model, @ModelAttribute ReplyVO rvo) {
		int replyIns = boardService.svcReplyInsert(rvo);
		return "redirect:/board_detail?seq="+rvo.getSeq();	// 방법1 => /board_detail?seq=3 (여러개라면 하나하나 작성하기 힘들다. || 주소에서 주소로 넘기는 방법.
		// 포워딩 => 내가 가진 request, response 모든것을 넘겨준다. 대신 페이지는 내것.
		// return "forward:/board_detail";
		// 내가 가진 모든 것이 넘어가기 때문ㅇ에 seq 안적어도 되지만, 제어권는 여전히 insert에 있어서 새로고침할 때 마다 insert가 계속 되기 때문에
		// 절대 insert에서는 forward 사용 불가.
	}
		
	
	@RequestMapping(value="/reply_delete")
	public String ctlReplyDelete(Model model, ReplyVO rvo) {
		int deleteRows = boardService.svcReplyDelete(rvo);
		return "redirect:/board_detail?seq="+rvo.getSeq(); 
	}
	
}

