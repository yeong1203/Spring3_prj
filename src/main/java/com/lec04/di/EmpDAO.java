package com.lec04.di;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.lec02.servlet.MyOracleConnection;

//new 하라는 이야기 & DAO 라는 뜻.
@Repository

// 1. DAO로 동작
// 2. 인스턴스(new) 생성 : <bean name="MY_EMPCTL_BEAN_NAME" class="com.lec04.di.EmpController" scope="singleton"> == 메모리에 올려라!

public class EmpDAO {
	
// 생성자를 받기 위한 처리.
//	private EmpVO evo;
//	public EmpDAO(EmpVO evo) {
//		this.evo = evo;
//	}
	
	public ArrayList<EmpVO> empSelect() {
		Connection conn = null;
		PreparedStatement pstmt  = null;
		ResultSet rs = null;
		ArrayList<EmpVO> list = new ArrayList<EmpVO>();
		DataSource ds = null;
		MyOracleConnection moc = new MyOracleConnection();  //클래스 분리시켜놓아서 인스턴스 생성해서 사용
		try {
			//---------------DBCP를 사용한 DB 연결 -----------------------
			//conn = moc.oracleConn();
			ds = moc.myOracleDataSource();
			conn = ds.getConnection();  
			String sql = "select empno, ename, nvl(sal,0) as sal from emp";
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			while(rs.next()) {
				EmpVO evo = new EmpVO();
				evo.setEmpno(rs.getInt("empno"));
				evo.setEname(rs.getString("ename"));
				evo.setSal(rs.getInt("sal"));
				list.add(evo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			moc.oracleClose(conn, pstmt, rs);
		}
		return list;
	}
	
}
