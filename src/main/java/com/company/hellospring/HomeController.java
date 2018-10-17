package com.company.hellospring;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import coupling.TV;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//@Autowired TV tv; // 객체 생성해서 주입해달라는 요청
	
	@RequestMapping(value = "/", method = RequestMethod.GET) //url 요청
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//tv.powerOn(); // @Autowired있으면 정상동작 
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/board.do")
	public String board(UserBean user) {
		System.out.println(user.getId()+"===========");
		return "board"; // 단지 board.jsp로 넘겨주는 역할.
	}
	
	@RequestMapping("/LoginCheck.do")
	@ResponseBody //data(자바객체) --> json string 으로 변환시켜줌
	//map : 2개의 field를 가지는 객체
	public HashMap<String, Object> dupcheck(UserBean user){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userid", user.getId());
		if(user.getId().equals("admin")){
			map.put("result",false);
		} else {
			map.put("result",true);
		}
		return map; //응답결과가 그냥 데이터. 뷰페이지 아님
	}
	
	@RequestMapping("/LoginCheckXml.do")
	@ResponseBody
	public UserBean dupcheckXml(UserBean user) {
		if(user.getId().equals("admin")) {
			user.setResult(false);
		}else {
			user.setResult(true);
		}
		return user;
	}
}
