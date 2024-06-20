//package com.lec04.di.board;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class BoardCallTest {
//
//	public static void main(String[] args) {
//		BoardDAO dao = new BoardDAO();
//		/**
//		 * 호출 방법
//		 * 
//		 */
//		//------ JOIN
////		BoardVO bvo = dao.boardReplySelect(1);
//		BoardVO bvo = dao.boardSelectOne(1); // 여기서 replies가 업데이트 되나요? 그냥 board만 반환하지 않나요?
//		int seq = bvo.getSeq();
//		String title = bvo.getTitle();
//		System.out.println(seq+"\t"+title);
//			
//		List<ReplyVO> rlist = bvo.getReplies(); 
//		for(ReplyVO rvo : rlist) {
//			int rseq = rvo.getSeq();
//			String reply = rvo.getReply();
//			System.out.println("\t\t"+rseq+"\t"+reply);
//		}
//		
//		// ----  상세보기 + 댓글		
//		BoardVO ovo1 = dao.boardSelectOne(1);
//		System.out.println(ovo1.getSeq()+", "+ovo1.getTitle());
//
//		
//		List<ReplyVO> rlist2 = dao.replySelect(1);
//		for(ReplyVO rvo : rlist2) {
//			int rseq = rvo.getRseq();
//			String reply = rvo.getReply();
//			System.out.println("\t\t"+rseq+"\t"+reply);
//		}
//		
//		System.out.println("----------------");
//		
//
//		
//		//public ArrayList<BoardVO> boardSelect() {}
////		ArrayList<BoardVO> list = dao.boardSelect();
////		System.out.println("총 : " + list.size());
////		for(BoardVO bvo : list) {
////			int seq         = bvo.getSeq();
////			String title    = bvo.getTitle();
////			String contents = bvo.getContents();
////			String regid    = bvo.getRegid();
////			String regdate  = bvo.getRegate();
////			System.out.println(seq + "\t" + title + "\t" + contents + "\t" + regid + "\t" + regdate);			
////		}
//				
//		//public int boardDelete(int seq) {}
////		int delRows = dao.boardDelete(12);
////		System.out.println(delRows + "건 삭제");
//		
//		//public ArrayList<BoardVO> boardSelect() {}
////		list = dao.boardSelect();
////		System.out.println("총 : " + list.size());
////		for(BoardVO bvo : list) {
////			int seq         = bvo.getSeq();
////			String title    = bvo.getTitle();
////			String contents = bvo.getContents();
////			String regid    = bvo.getRegid();
////			String regdate  = bvo.getRegate();
////			System.out.println(seq + "\t" + title + "\t" + contents + "\t" + regid + "\t" + regdate);			
////		}
//		
////		// 값 하나만 불러오는 것.
////		//public BoardVO boardSelectOne(int seq) {}
////		BoardVO ovo = dao.boardSelectOne(3);
////		System.out.println(ovo);
////		System.out.println(ovo.getSeq() + "\t" + ovo.getTitle());
////
////		System.out.println("----------------");
////		
////		BoardVO bvo  = new BoardVO();
////		bvo.setTitle("title...");
////		bvo.setContents("contents...");   	
////		bvo.setSeq(1);   		
////		int updateRows = dao.boardUpdate(bvo);
////		System.out.println(updateRows + "건 수정");
////		
////		bvo  = new BoardVO();
////		bvo.setTitle("title222...");
////		bvo.setContents("contents222...");   	
////		bvo.setRegid("zzzz");   
////		int insertRows = dao.boardInsert(bvo);
////		System.out.println(insertRows + "건 입력");		
//		
//
////		for(int i=0; i<list.size(); i++) {
////			int seq         = list.get(i).getSeq();
////			String title    = list.get(i).getTitle();
////			String contents = list.get(i).getContents();
////			String regid    = list.get(i).getRegid();
////			String regdate  = list.get(i).getRegdate();
////			System.out.println(seq + "\t" + title + "\t" + contents + "\t" + regid + "\t" + regdate);			
////		}
//		
//		/**
//		MyOracleConnection moc = new MyOracleConnection();
//		BoardDAO dao = new BoardDAO();
//		ArrayList<BoardVO> list = dao.boardSelect();
//
//		// public int boardDelete(int seq)
//		int delRows = dao.boardDelete(3);
//		System.out.println(delRows +"건 삭제");
//		
//		
//		// public ArrayList<BoardVO> boardSelect()
//		System.out.println("총: "+ list.size());
////		for(int i=0; i<list.size(); i++) {
//		for(BoardVO bvo : list) {
//			int seq = list.get(i).getSeq();
//			String title = list.get(i).getTitle();
//			String contents = list.get(i).getContents();
//			String regid = list.get(i).getRegid();
//			String regdate = list.get(i).getRegate();
//			System.out.println(seq+"\t"+title+"\t"+contents+"\t"+regid+"\t"+regdate);
//		}
//	 */
//	}
//
//}
