package com.lec04.di;

//import java.io.IOException;
//import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;  				// @Controller 얘꺼
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;	// @Autowired 
import org.springframework.web.bind.annotation.RequestMapping;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// @ 어노테이션으

//@Controller
// == <bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController" />

public class EmpController extends MultiActionController {   //extends HttpServlet {
	
	//-------------------------어노테이션------------------------------------------
	//<bean name="MY_EMPDAO_BEAN_NAME" class="com.lec04.di.EmpDAO" />
	@Autowired	//  EmpDAO dao = new EmpDAO(); 스프링컨테이너가 dao를 new()해서 아래 멤버변수 dao에 주입
	// @Autowired == 주세요! 하는 것.
	//private EmpDAO dao;		
	
	
	// --------------------------XML기반------------------------------------------
	// 생성자
//	private EmpDAO dao;
//	public EmpController(EmpDAO dao) {
//		this.dao = dao;
//	}	
	
	// 프로퍼티(setter)
	private EmpDAO dao;
	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	
	private String str;		// 프로퍼티 추가 된 것.
	public void setStringTest(String str) {
		this.str = str;
	}
	

	
	
								// 이 주소로 들어오면 실행.
	//@RequestMapping(value = "/emp_list_servlet", method = RequestMethod.GET)
	public ModelAndView empList(HttpServletRequest request, HttpServletResponse response) {	
//		//EmpDAO dao = new EmpDAO();
//		ArrayList<EmpVO> list = dao.empSelect();
//		model.addAttribute("KEY_EMPLIST", list);
//		return "lec02_servlet";
		
		// setter(property방식)
		ModelAndView mav = new ModelAndView();
		// Model
		ArrayList<EmpVO> list = dao.empSelect();
		mav.addObject("KEY_EMPLIST", list );
		mav.addObject("KEY_TESTSTR", this.str);
		// view
		mav.setViewName("lec02_servlet");
		System.out.println("DI로 받은 스트링값: "+ this.str);
		return mav;
		 
	}
	
	
	public ModelAndView empDumy(HttpServletRequest request, HttpServletResponse response) {	

		ModelAndView mav = new ModelAndView();
		// Model
		mav.addObject("KEY_EMPLIST", new ArrayList<EmpVO>() );
		mav.addObject("KEY_TESTSTR", "empDumy : DumyTest");
		// view
		mav.setViewName("lec02_servlet");
		return mav;
		 
	}
	
	
	

// 서블릿처럼 doGet/doPost 처럼 주소를 주지 못하기 때문에 이 것도 사용 못함. Controller 다음 위치의 AbstractController 였음.
//	말이 핸들러이지 실은 기존 작업과 다를 것이 없다. 주소도 줄 수 없음.	
//	import org.springframework.web.servlet.mvc.AbstractController;
//	@Override
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	// MultiActionController 얘를 상속 받았는데, 조건도 없어서 내가 원하는 이름으로 그냥 사용할 수 있고, 내 마음대로 사용 가능.
	// 내 마음대로 메서드 이름 지으면 되고, request & response 가 있고, 니가 더 받고 싶은것을 더 작성해서 받아라. 
	// 즉, public String empList(HttpServletRequest request, HttpServletResponse response , <내가 더 받고싶은 것> ) { 
	// return 
}

