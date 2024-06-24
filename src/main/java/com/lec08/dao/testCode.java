package com.lec08.dao;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class testCode {
	@Autowired
	DataSource ds;

	public static void main(String[] args) {

		BoardDAO bvo = new BoardDAO();
		BoardVO blist = bvo.boardSelectOne(3);
//		ArrayList<BoardVO> blist = bvo.boardList();
		System.out.println(blist);
		
	}

}
