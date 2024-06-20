package com.lec04.di.board;

public class test {
	public static void main(String[] arg) {
		BoardDAO dao = new BoardDAO();
		BoardVO bvo = new BoardVO();
		
		bvo.setTitle("test");
		bvo.setContents("testeeeee");
//		System.out.println(dao.boardInsert(bvo));
		
		
	}
}
