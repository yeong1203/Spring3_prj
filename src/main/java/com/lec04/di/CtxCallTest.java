package com.lec04.di;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.lec03.spring.EmpDAO;
import com.lec03.spring.EmpVO;

public class CtxCallTest {

	/**
	   기능 : xml을 읽어 해당 설정에 있는 (클래스들의 인스턴스 초기화) == (new)
	    <interface>				<class>
	    BeanFactory    			XmlBeanFactory
		ApplicationContext   	ClassPathXmlApplicationContext		// : src/main/resources
								FileSystemXmlApplicationContext		// : file pull path (파일 풀 경로)
		WebApplicationContext	XmlWebApplicationContext			// 위에서 한 것 + session & request... 추가로 더 꺼낼 수 있음.
	 */
	public static void main(String[] args) {
		
		String xmlFile = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec04-servlet-context.xml";
        ApplicationContext ctx = new FileSystemXmlApplicationContext(xmlFile);
        // 경로를 찾아서 new 처리 다 함.
        /*
         * <beans:bean name="MY_IR_VIEW_RESOLVER_BEAN_NAME"   class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<beans:property name="prefix" value="/" />
				<beans:property name="suffix" value=".jsp" />
			</beans:bean>
			
			import org.springframework.web.servlet.view.InternalResourceViewResolver;
			InternalResourceViewResolver irvr = new InternalResourceViewResolver();
			
         */
        // new 처리된 bean을 가지고 와라! 해서 꺼내는 것인데, 오브젝트로 넘어오기 때문에 꼭 캐스팅해야함.
        InternalResourceViewResolver ds = (InternalResourceViewResolver)ctx.getBean("MY_IR_VIEW_RESOLVER_BEAN_NAME");
        if (ds != null) {
        	System.out.println("인스턴스생성" + ds);
        } else {
        	System.out.println("인스턴스생성 실패");
        }
        
        //-----POJO(Plan Old Java Object): 결합도가 높아 비효율적이다. --------------------------------------
        EmpDAO dao = new EmpDAO();
        ArrayList<EmpVO> list = dao.empSelect();
        System.out.println(list.size() + "건 - new");
        
        //---------- DI(Dependency Injection) : 결합도를 낮춰 상당히  효율적 (재사용 , 확장) ------------------------------
        /* 
         * <beans:bean name="MY_EMPDAO_BEAN_NAME" class="com.lec03.spring.EmpDAO"></beans:bean> 
         * == EmpDAO dao = new EmpDAO();
         * */
        
        /* 스프링이 해 주는 일을*/
        String xmlFile04 = "C:\\IT\\S3917_J11\\workspace_sts3\\spring3_prj\\src\\main\\webapp\\WEB-INF\\spring\\lec04-servlet-context.xml";
        ApplicationContext ctx4 = new FileSystemXmlApplicationContext(xmlFile04);
        EmpDAO sdao = (EmpDAO)ctx4.getBean("MY_EMPDAO_BEAN_NAME");
        
        ArrayList<EmpVO> list4 = sdao.empSelect();
        System.out.println(list4.size() + "건 - 스프링사용");
        
        // 추후 수정이나 확장이 용이하고 결합도를 낮춰서 좋은 코드이다.
	}

}
