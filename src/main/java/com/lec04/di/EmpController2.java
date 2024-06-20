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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;	// @RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

//public class EmpController2 extends MultiActionController {
// @Controller == <bean name="MY_EMPCONTROLLER_BEAN_NAME" class="com.lec04.di.EmpController" />

@Controller
	// 1. 컨트롤러로 동작하게 해주는 것. : extends ** Controller (컨트롤러 상속한다!)
	// 2. 인스턴스(new) 생성 : <bean name="MY_EMPCTL_BEAN_NAME" class="com.lec04.di.EmpController" scope="singleton"> == 메모리에 올려라!
public class EmpController2 {
	// ==== 어노테이션으로 작업하는 방법.
	// 생성자를 대신하는 골뱅이 없음. => @자체가 메모리에 올려서 인스턴스화(new해서 초기화) 해라 인데, 생성자는 어차피 초기화시키는 것 이기 때문에 의미가 없다.
	@Autowired		// DAO 대신하는 골뱅이 = Autowired
	// 1. <property name="dao" ref="MY_EMPDAO_BEAN_NAME" />
	private EmpDAO dao;	
	
	@Value("abcd")  // 바로 값을 주입 
	// <property name="stringTest" value="abc" />
	private String str;
	// private
							// 이 주소로 들어오면 실행.
	@RequestMapping(value = "/emp_list", method = RequestMethod.GET)
	public String empList(Model model) {	
		ArrayList<EmpVO> list = dao.empSelect();
		model.addAttribute("KEY_EMPLIST", list);
		model.addAttribute("KEY_TESTSTR", this.str); // this의 abc
		return "lec02_servlet";		 
	}
	@RequestMapping(value = "/emp_dummy", method = RequestMethod.GET)
	public String empDumy(Model model) {	
		ArrayList<EmpVO> list = dao.empSelect();
		model.addAttribute("KEY_EMPLIST", new ArrayList<EmpVO>() );
		model.addAttribute("KEY_TESTSTR", "empDumy : DumyTest");
		return "lec02_servlet";
		 
	}
	
	
	// 아래처럼 작업하면 됨. 예시는 emplist만 남김.
							// 이 주소로 들어오면 실행.
//	@RequestMapping(value = "/emp_list", method = RequestMethod.GET)
//	public ModelAndView empList(HttpServletRequest request, HttpServletResponse response) {	
////		//EmpDAO dao = new EmpDAO();
////		ArrayList<EmpVO> list = dao.empSelect();
////		model.addAttribute("KEY_EMPLIST", list);
////		return "lec02_servlet";
//		
//		ModelAndView mav = new ModelAndView();
//		// Model
//		ArrayList<EmpVO> list = dao.empSelect();
//		mav.addObject("KEY_EMPLIST", list );
//		mav.addObject("KEY_TESTSTR", this.str);
//		// view
//		mav.setViewName("lec02_servlet");
//		System.out.println("DI로 받은 스트링값: "+ this.str);
//		return mav;
//		 
//	}
	

	
	

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

