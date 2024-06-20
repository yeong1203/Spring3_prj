package com.lec05.board;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lec05.board.BoardDAO;
import com.lec05.board.BoardVO;

@Controller
public class TestControllerForRest {
	
	@Autowired
	private BoardDAO dao;
	/**
	// 4가지연습, 서블릿하고 컨트롤러 왔다 갔다 하는것.
	@RequestMapping(value="/sample", method=RequestMethod.POST
					, consumes = "application/json"  // consumes는 앞으로 데이터 들어올 것을 json형태로 해달라는 뜻.
					, produces = "application/json"  // 내보낼 데이터 모양도 json입니다.
	)
	@ResponseBody  // httpResponseBody에 글자를 내려보내는것. (HTTP Response in Body) == 컨트롤러로 이동하지않고, jsp파일로 이동하지 않는다.
	// 즉, HTTP 바디에 응답을 실어서 보내라.
	public String ctlSample(Model model, @RequestParam("seq") int seq) {  // @ModelAttribute BoardVO bvo 는 seq 하나 받기에 너무 큼
		//BoardVO bvo = boardDAO.boardDelete(seq);							@ModelAttribute는 String. 즉, key = val 구성으로 form의 입력값들.
		//return "redirect:/board_list";		-- controller 이동
		//return "lec04_board/board_detail";  -- jsp 이동
		//return "200 ok";
	}
	
//		PrintWriter out = response.getWriter();
//		out.println("200 ok");
//		이 두줄이 @ResponseBody 와 동일함.
 * 
 * 
 * 	@RequestMapping(value="/ctl_str_str", method=RequestMethod.POST
			, consumes = "application/json"  // consumes는 앞으로 데이터 들어올 것을 json형태로 해달라는 뜻.(서버가 받아들일 데이터(클라이언트 요청 데이터))
			, produces = "application/json"  // 내보낼 데이터 모양도 json입니다.						(서버가 내보낼 데이터  (서버 응답 데이터))
			) // consumes는 앞으로 데이터 들어올 것을 json형태로 해달라는 뜻.
			// consumes = "application/json" 즉, 들어올 데이터가 JSON입니다.
	@ResponseBody
	public String ctlStrStr(Model model, @RequestParam("ename") String ename) {	
		System.out.println(ename);
		return "200 ok";		// insert 처리 후 board 리스트로 이동해서 board_list에서 처리.		 
	}
 * 
 * 
	*/
	
	@RequestMapping(value="/ctl_str_str", method=RequestMethod.POST )
	@ResponseBody
	public String ctlStrStr(Model model, @RequestParam("ename") String ename) {	
		System.out.println(ename);
		return "1. 200 ok";				 
	}
	
	
	@RequestMapping(value="/ctl_json_str", method=RequestMethod.POST
					, consumes = "application/json")
	@ResponseBody
	public String ctlJsonStr(Model model, @RequestBody BoardVO bvo) { //@RequestBody BoardVO bvo : 클라이언트로부터 JSON으로 들어올 때, 사용.	
		System.out.println(bvo.getRegid()+", "+ bvo.getTitle());
		return "2. 200 ok";				 
	}
	
	
	@RequestMapping(value="/ctl_str_json", method=RequestMethod.POST
					, produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, String>> ctlStrJson(Model model
						, @RequestParam("ename") String ename) { //@RequestBody BoardVO bvo : 클라이언트로부터 JSON으로 들어올 때, 사용.	

		System.out.println(ename);
		
		Map<String, String> map = new HashMap<String, String>();
		// put 처리한 부분은 내가 넣은 것.
		map.put("status", "200");
		map.put("message", "서버 응답 OK");
		
		return new ResponseEntity<>(map, HttpStatus.OK);	// json으로 return ( response에 객체를 담아서 보내야함. 응답에 객체 담아랏! ) 			 
		// 보낼때, 객체 그대로 넘겨준다. 
		// HttpStatus.OK ==> 
	}
	
	
	@RequestMapping(value="/ctl_json_json", method=RequestMethod.POST,  consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Map<String, String>> ctlJsonJson(Model model
					, @RequestBody BoardVO bvo) {
	System.out.println(bvo.getRegid()+", "+bvo.getTitle());
	
	Map<String, String> map = new HashMap<String, String>();
	// put 처리한 부분은 내가 넣은 것.
	map.put("status", "200");
	map.put("message", "서버 응답 OK");
	
	return new ResponseEntity<>(map, HttpStatus.OK);	// json으로 return ( response에 객체를 담아서 보내야함. 응답에 객체 담아랏! ) 			 
	// HttpStatus.OK ==> 
	}

	
	@RequestMapping(value="/ctl_normal", method=RequestMethod.POST)  //, produces = "application/json")
	@ResponseBody  // body에 실린거 그대로 실행 함. 그래서 리턴시,<> 따로 지정 안함.
	public ResponseEntity<ArrayList<BoardVO>> ctlNomal(Model model, @ModelAttribute BoardVO bvo) {
		System.out.println(bvo.getRegid()+", "+bvo.getTitle());
		model.addAttribute("title", bvo.getTitle());
		model.addAttribute("regid", bvo.getTitle());
		System.out.println(model);
		ArrayList<BoardVO> list = dao.boardList(); 
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
