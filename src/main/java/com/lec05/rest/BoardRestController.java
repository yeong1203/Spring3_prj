package com.lec05.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lec04.di.board.BoardDAO;
import com.lec04.di.board.BoardVO;

@Controller
public class BoardRestController {
	
	@Autowired 
	private BoardDAO boardDAO;

	
	//http://localhost:8007/_ctxpath_/board_detail_rest?seq=3
	@RequestMapping(value="/board_detail_rest")
	public String ctlBoardDetail(Model model, @RequestParam("seq") int seq) {  // @ModelAttribute BoardVO bvo 는 seq 하나 받기에 너무 큼
		BoardVO bvo = boardDAO.boardReplySelect(seq);
		model.addAttribute("KEY_BOARDDETAIL", bvo);
		return "lec04_board/board_detail";
	}
}
