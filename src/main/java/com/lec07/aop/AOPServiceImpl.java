package com.lec07.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AOPServiceImpl implements AOPService {
		
		@Autowired
		private AOPDAO aOPDAO;

		@Override
		public void svcDelete() {
			System.out.println("2.___AOPServiceImpl.svcDelete() 실행");
			aOPDAO.delete();
			
			// 강제 에러 발생
			//throw new RuntimeException();
		}
		
}
