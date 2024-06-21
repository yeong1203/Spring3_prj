package com.lec07.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component    // 역할 구분을 위해 구분해서 사용해야함.
@Aspect		// 공통기능이다. 메모리에 초기화해서 올려라. :: Aspect가 붙으면 AnnotationAwareAspectJAutoProxyCreator가 현재 클래스를 Proxy 대상으로 설정한다. (set된다). 메서드 실행시 프록시가 여기꺼를 부른다. 
public class CommonAspect {
	
	@Pointcut("execution(public * com.lec07.aop..*DAO.*(..))")
	public void dummyDAOCut() { }
	
	@Pointcut("execution(public * com.lec07.aop..*Impl.*(..))")  // "execution(public * com.lec07.aop..*Impl.*(..))" 얘를 지칭하는 dummyImplCut(){} 를 호출.
	public void dummyImplCut() { }
	
	
//	@Before("dummyImplCut()")
	@Before("dummyDAOCut()")
	public void beforeAdvice() {
		System.out.println("\t 실행전::CommonAspect.beforeAdvice()");
	}
	@After("dummyImplCut()")
	public void afterAdvice() {  // 실행 후 무조건 처리되는 애라서 finally 같은 개념.
		System.out.println("\t 실행후무조건::CommonAspect.afterAdvice()");
	}
//	@AfterThrowing(pointcut="dummyImplCut()", throwing="ex")
//	public void afterThrowingAdvice(Exception ex) {
//		System.out.println("\t 실행후에러시::CommonAspect.afterThrowingAdvice() :: " + ex.getMessage());
//	}
	@AfterReturning(pointcut="dummyImplCut()", returning="res")
	public void afterReturningAdvice(Object res) {
		System.out.println("\t 실행후정상시::CommonAspect.afterReturningAdvice() :: " + res);
	}
	@Around("dummyImplCut()")
	public void aroundAdvice(ProceedingJoinPoint jp) {
		
		try {
			System.out.println("\t 앞-CommonAspect.aroundAdvice()");
			System.out.println("\t  :: " + jp.getSignature());   // method이름만 찍어봄.
			jp.proceed();    	// aOPService.svcDelete();
			System.out.println("\t 뒤-CommonAspect.aroundAdvice()");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}	
}
