package com.lec08.dao;

import javax.naming.Context; 
import javax.naming.InitialContext; 
import javax.sql.DataSource; 
import java.sql.Connection; 
public class JNDICallTest {
	public static void main(String[] args) {
		try {
			Context initialContext = new InitialContext();
			DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/jdbc/MyDataSource_MYNAME");
																// 여기 ("java:comp/env/[name]") name이 동일해야함. -- context.xml(tomcat) & web.xml 
			Connection conn = dataSource.getConnection(); 
			if(conn != null) {
				System.out.println("conn ok");
			}else {
				System.out.println("faild");
			}
			
			conn.close(); 
		}
		catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}