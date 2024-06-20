package com.lec05.rest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lec04.di.board.BoardDAO;
import com.lec04.di.board.BoardVO;

@RestController
public class TestRestControllerForRest {
	/**
	 * @ResponseBody 사용하지 X
	 * @RestController는 @controller + @ResponseBody, 즉 사용하지 않아도 무조건 응답 보낸당.
	 * 
	 */
	
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping(value="/restctl_str_str", method=RequestMethod.POST )
	public String ctlStrStr(Model model, @RequestParam("ename") String ename) {	
		System.out.println(ename);
		return "0. Rest 200 ok";				 
	}
	
	
	
}
