package com.lec04.di.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class BoardServiceImpl implements BoardService {


	@Autowired 
	private BoardDAO boardDAO;

	public int svcBoardInsert(@ModelAttribute BoardVO bvo) {	
		int insertRows = boardDAO.boardInsert(bvo);
		return insertRows;		// insert 처리 후 board 리스트로 이동해서 board_list에서 처리.		 
	}
	
	public ArrayList<BoardVO> svcBoardList() {	
		ArrayList<BoardVO> list = boardDAO.boardList();
		return list;
	}
	
	public Map svcBoardDetail(int seq) {  
		//------------------------------------------------------
		//상세,댓글목록 각각 사용할 경우
		//	트랜잭션 : SELECT(게시물상세) , SELECT(댓글목록) 
		//	게시물상세 EL : ${KEY_BOARDVO} () 
		//	댓글목록 EL  : ${KEY_REPLYLIST}
		//------------------------------------------------------
		BoardVO bvo              = boardDAO.boardSelectOne(seq);
		ArrayList<ReplyVO> rlist = boardDAO.replySelect(seq);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("my_bvo", bvo);
		map.put("my_rlist", rlist);
		return map;
	}
	
	
	public int svcBoardUpdate(BoardVO bvo) {
		int updateRows = boardDAO.boardUpdate(bvo);
		return updateRows;
	}
	
	public int svcBoardDelete(int seq) {
		int deleteRows = boardDAO.boardDelete(seq);
		return deleteRows;
	}
	
	public int svcReplyInsert(ReplyVO rvo) {
		int insertRows = boardDAO.replyInsert(rvo);
		return insertRows;	
	}
		
	public int svcReplyDelete(ReplyVO rvo) {
		int deleteRows = boardDAO.replyDelete(rvo.getRseq());
		return deleteRows; 
	}
	
}
