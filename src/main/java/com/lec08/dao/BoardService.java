package com.lec08.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


// @사용금지. interface는 new안됨.
// 모든 걸 받기위한 틀.
public interface BoardService {
	public int svcBoardInsert(BoardVO bvo);
	
	public ArrayList<BoardVO> svcBoardList();
	
	public Map svcBoardDetail(int seq);
	
	
	public int svcBoardUpdate(BoardVO bvo) ;
	
	public int svcBoardDelete(int seq) ;
	
	public int svcReplyInsert(ReplyVO rvo) ;
		
	public int svcReplyDelete(ReplyVO rvo) ;
}
