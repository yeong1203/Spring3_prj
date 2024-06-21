package com.lec06.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class CommonAspect {
	// void로 5개 생성.
	public void beforeAdvice() {
		System.out.println("\t 실행전::CommonAspect.beforeAdvice()");
	}
	
	public void afterAdvice() {  // 실행 후 무조건 처리되는 애라서 finally 같은 개념.
		System.out.println("\t 실행후무조건::CommonAspect.afterAdvice()");
	}
	
	public void afterThrowingAdvice(Exception ex) {
		System.out.println("\t 실행후에러시::CommonAspect.afterThrowingAdvice() :: " + ex.getMessage());
	}
	
	public void afterReturningAdvice(Object res) {
		System.out.println("\t 실행후정상시::CommonAspect.afterReturningAdvice() :: " + res);
	}
	
	public void aroundAdvice(ProceedingJoinPoint jp) {
		
		try {
			System.out.println("\t 앞-CommonAspect.aroundAdvice()");
			System.out.println("\t  :: " + jp.getSignature());   // method이름만 찍어봄.
			jp.proceed();    // aOPService.svcDelete();
			System.out.println("\t 뒤-CommonAspect.aroundAdvice()");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
}
