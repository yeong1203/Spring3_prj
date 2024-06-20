package com.lec04.di.board;

import java.util.List;

public class BoardVO {
	private int seq;			// PK
	private String title;
	private String contents;
	private String regid;		// session cookei
	private String regdate;		// default sysdate
	
	List<ReplyVO> replies;		// 1:N ==> 댓글은 정해진 수량이 없기 때문에 List 로 받음.
	// new 해서 들어오는 것 child -> 다형성을 위해서 
	
	// Board
	public BoardVO() {}		// 기본생성자 -- 에러 방지를 위해 작성하기.
	
	public BoardVO(int seq, String title, String contents, String regid, String regdate) {
		super();
		this.seq = seq;
		this.title = title;
		this.contents = contents;
		this.regid = regid;
		this.regdate = regdate;
	}


	
	// 댓글까지 받는 것.
	public BoardVO(int seq, String title, String contents, String regid, String regdate, List<ReplyVO> replies) {
		//super();
		this(seq, title, contents, regid, regdate);
		this.replies = replies;
	}
	
	public List<ReplyVO> getReplies() {
		return replies;
	}

	public void setReplies(List<ReplyVO> replies) {
		this.replies = replies;
	}

	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegid() {
		return regid;
	}
	public void setRegid(String regid) {
		this.regid = regid;
	}
	public String getRegate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", contents=" + contents + ", regid=" + regid + ", regdate="
				+ regdate + ", replies=" + replies + "]";
	}
	
	

}
