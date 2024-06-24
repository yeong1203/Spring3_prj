package com.lec08.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.lec08.dao.BoardVO;
import com.lec08.dao.ReplyVO;

//import jdk.internal.org.jline.terminal.TerminalBuilder.SystemOutput;

@Repository
public class BoardDAO {
	
	@Autowired
	DataSource ds;
	
	// 리스트
	public ArrayList<BoardVO> boardList() {	// 파라미터 없음. 원하는 값을 주기위해서 List처리한 것.
		// boardVO를 통해 get해서 데이터 꺼내기.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();

		try {
			conn = ds.getConnection(); 
			String sql = "select * from board order by seq desc";
			pstmt = conn.prepareStatement(sql);		// 바인딩해서 실행시점에 사용하겠다!  없으면 없는데로, 있으면 있는대로 사용이 가능함.
			rs = pstmt.executeQuery();
			
			while(rs.next()) {		// TRUE 일때까지 WHILE처리.
				BoardVO bvo = new BoardVO();
				bvo.setSeq(rs.getInt("seq"));		// set으로 받았기 때문에  get으로 꺼내기.
				bvo.setTitle(rs.getString("title"));
				bvo.setContents(rs.getString("contents"));
				bvo.setRegid(rs.getString("regid"));
				bvo.setRegdate(rs.getString("regdate"));
				list.add(bvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		catch 다음 finally 생략
//		finally {
//			oracleClose(conn, pstmt, rs);
//		}
//		lec08-servlet-context.xml 파일에서 datasource설정 
//		=> destroy-method="close"로 자동으로 close 처리되어 있음. 
//		그래서 finally 처리 및 서버 close 자체를 해 줄 필요도 없다. 
		
		return list;
	}
	
	// 조인 X, 리스트
	public ArrayList<BoardVO> boardSelect() {	
		Connection conn = null;
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		//DataSource ds = null;
		//MyOracleConnection moc = new MyOracleConnection();  //클래스 분리시켜놓아서 인스턴스 생성해서 사용
		try {
			//---------------DBCP를 사용한 DB 연결 -----------------------
			//conn = moc.oracleConn();
			//ds = moc.myOracleDataSource();
			conn = ds.getConnection();  
			String sql = "select * from board order by seq desc";
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setSeq(rs.getInt("seq"));
				bvo.setTitle(rs.getString("title"));
				bvo.setContents(rs.getString("contents"));
				bvo.setRegid(rs.getString("regid"));
				bvo.setRegdate(rs.getString("regdate"));
				list.add(bvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	// 게시글 등록
	public int boardInsert(BoardVO bvo) {	// 개별컬럼이 아닌 다 들어 있는 vo를 넣어주면 된다. 
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertRows = 0;
		
		//MyOracleConnection moc = new MyOracleConnection();	//클래스 분리시켜놓아서 인스턴스 생성해서 사용.

		try {
			//moc.myOracleDataSource();
			//conn = moc.oracleConn();
			conn = ds.getConnection();
			String sql ="insert into board values (board_seq.nextval, ?,?,'dd',sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());   	
			insertRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return insertRows;
	}
	
	// detail
	// boardSelectOne(int seq)
	public BoardVO boardSelectOne(int seq) {
		// 한 것만 가져올 것이기 때문에 그냥 BoardVO 를 담아 가져오는 것.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//DataSource ds = null;
		//MyOracleConnection moc = new MyOracleConnection();
		
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO bvo = null;		
		try {
			//ds = moc.myOracleDataSource();
			conn = ds.getConnection();
			String sql = "select seq, title, contents, regid, regdate from board where seq=?";	// 바인딩
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			rs.next(); 	// while 필요 없다. 한 줄만 읽으면 된다. 그래서 next()만 필요해.
				
			System.out.println(rs.toString());
			bvo = new BoardVO();
			bvo.setSeq(rs.getInt("seq"));
			bvo.setTitle(rs.getString("title"));
			bvo.setContents(rs.getString("contents"));
			bvo.setRegid(rs.getString("regid"));
			bvo.setRegdate(rs.getString("regdate"));
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	
		return bvo;
	}
	
	// 조인 없이 가져오기. --댓글
	public ArrayList<ReplyVO> replySelect(int seq) {
		ArrayList<ReplyVO> list = new ArrayList<ReplyVO>();
		// 변수들
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// DB
		//DataSource ds = null;
		//MyOracleConnection moc = new MyOracleConnection();
		try {
			//ds = moc.myOracleDataSource();
			conn = ds.getConnection();
			// sql
			String sql = "select seq, rseq, reply, regid, regdate from reply where seq=? order by rseq desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);				// 1번째 값에 seq 넣어주세요.
			rs = pstmt.executeQuery();
			// while -> 
			while(rs.next()) {		// TRUE 일때까지 WHILE처리. => rseq, reply, regid, regdate 
				ReplyVO rvo = new ReplyVO();
				rvo.setSeq(rs.getInt("seq"));
				rvo.setRseq(rs.getInt("rseq"));
				rvo.setReply(rs.getString("reply"));
				rvo.setRegid(rs.getString("regid"));
				rvo.setRegdate(rs.getString("regdate"));
				list.add(rvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	

	// (댓글 게시글 조인) 디테일
//	public BoardVO boardReplySelect(int seq){
//		// 프로토타입 선언 : 메서드 이름짓고, 파라미터 뭘보 받고 리턴리스트 뱉어지게. => 이렇게 작성하기.
//		//ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		//return list;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		DataSource ds = null;
//		MyOracleConnection moc = new MyOracleConnection();
//		BoardVO bvo = new BoardVO();
//		try {
//			ds = moc.myOracleDataSource();
//			conn = ds.getConnection();
//
//			String sql = "select b.seq, b.title, b.contents, b.regid, b.regdate, "
//					+ "r.rseq , r.reply, r.regid as rregid, r.regdate as rregdate "
//					+ "from board b, reply r "
//					+ "where b.seq=? "
//					+ "and b.seq = r.seq(+) "
//					+ "order by r.rseq desc";	// 바인딩
//			pstmt = conn.prepareStatement(sql);		// 바인딩해서 실행시점에 사용하겠다!  없으면 없는데로, 있으면 있는대로 사용이 가능함.
//			pstmt.setInt(1, seq);					// 바인딩 하나 있어서 그 자리에 seq를 주는 것.
//			rs = pstmt.executeQuery();
//			
//			List<ReplyVO> replyList = new ArrayList<ReplyVO>();		// 댓글은 ReplyVO에 있는데 그걸 불러
////			ReplyVO rvo = new ReplyVO();
//			while(rs.next()) {		// TRUE 일때까지 WHILE처리.
//				bvo = null;
//				if(bvo == null) {	// null일때, 한번만 담아라! 이런 용도로 하는것! 덮어서 한번만 실행되긴 하지만 굳이 그렇게 하지 않고 null 조건을 줘서 실행.
//					bvo = new BoardVO();
//					bvo.setSeq(rs.getInt("seq"));		// set으로 받았기 때문에  get으로 꺼내기.
//					bvo.setTitle(rs.getString("title"));
//					bvo.setContents(rs.getString("contents"));
//					bvo.setRegid(rs.getString("regid"));
//					bvo.setRegdate(rs.getString("regdate"));					
//				}
//				
//				ReplyVO rvo = new ReplyVO();	// 불러온 글의 댓글을 찾아서 bvo에 붙여 넣은 것. 
//				rvo.setRseq(rs.getInt("rseq"));
//				rvo.setReply(rs.getString("reply"));
//				rvo.setRegid(rs.getString("rregid"));
//				rvo.setRegdate(rs.getString("rregdate"));
//				// BoardVO List<ReplyVO> replies;
//				// List<ReplyVO> replyList
//				replyList.add(rvo);		// 즉, board 1개에 rvo를 담아둠.
//				// 리스트에 rvo를 추가요!해서 넣은 것.
//			}
//			bvo.setReplies(replyList);	// 루프 밖에서 한번에 담으면 되기 때문에 밖에 저장.
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			moc.oracleClose(conn, pstmt, rs);
//		}		
//		return bvo;
//	}
	
	// 게시글 수정
	public int boardUpdate(BoardVO bvo) {	
		Connection conn = null;
		PreparedStatement pstmt  = null;
		int updateRows = 0;	// 제일 먼저 적기.

		try {
			conn = ds.getConnection();  

			String sql = "update board set title=?, contents=? where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getContents());   	
			pstmt.setInt(3, bvo.getSeq());   	
			updateRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateRows;	
	}

	// 게시글 삭제
	public int boardDelete(int seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delRows = 0;

		try {
			conn = ds.getConnection();  
			
			String sql = "delete from board where seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			delRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return delRows;	
	}
	
	// 댓글 등록
		public int replyInsert(ReplyVO rvo) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int insertRows = 0;

			try {
				conn = ds.getConnection();

				String sql ="insert into reply values(reply_seq.nextval, ?,'testid',sysdate,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, rvo.getReply());
				pstmt.setInt(2, rvo.getSeq());
				insertRows =  pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return insertRows;
		}
	
	// 댓글 삭제
	public int replyDelete(int rseq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int delRows = 0;
		//DataSource ds = null;		
		//MyOracleConnection moc = new MyOracleConnection();	
		
		try {
			//ds = moc.myOracleDataSource();
			conn = ds.getConnection();  
			
			String sql = "delete from reply where rseq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rseq);
			delRows =  pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return delRows;	
	}
	
	/*
	 * // ------------- close 공통 ---------------------- public void
	 * oracleClose(Connection conn, PreparedStatement pstmt, ResultSet rs) { try {
	 * if (rs!=null) rs.close(); if (pstmt!=null) pstmt.close(); if (conn!=null)
	 * conn.close(); } catch (SQLException e) { e.printStackTrace(); } } public void
	 * oracleClose(Connection conn, PreparedStatement pstmt) { try { if
	 * (pstmt!=null) pstmt.close(); if (conn!=null) conn.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 * 
	 */	
	
	
}
