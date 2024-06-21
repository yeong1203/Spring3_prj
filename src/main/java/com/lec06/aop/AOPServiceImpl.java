package com.lec06.aop;

public class AOPServiceImpl implements AOPService {

		// property 방식으로 DI 주입
		private AOPDAO aOPDAO;
		public void setAOPDAO(AOPDAO aOPDAO) {
			this.aOPDAO = aOPDAO;
		}
	
		@Override
		public void svcDelete() {
			System.out.println("2.___AOPServiceImpl.svcDelete() 실행");
			aOPDAO.delete();
			
			// 강제 에러 발생
			//throw new RuntimeException();
		}
		
}
