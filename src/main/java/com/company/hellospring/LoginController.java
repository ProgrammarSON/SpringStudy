package com.company.hellospring;

import java.net.HttpCookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller		//빈 등록, 서블릿이 호출하도록 설정
public class LoginController {

	@Autowired UserService userService;
	
	//로그인폼
	@RequestMapping(value= {"/login.do","/in.do"},method=RequestMethod.GET)
	public String login() {
		return "users/login";
	}
	
	//로그인 처리
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginProc(@ModelAttribute("user") UserDTO dto, HttpSession session) {
		UserDTO userDTO = userService.getUser(dto);
		if( userDTO == null || !userDTO.getPassword().equals(dto.getPassword()) ) {
			return "users/login";
		} else {
			session.setAttribute("login", userDTO);
			return "redirect:getUsers.do";
		}
	}
	
	//로그아웃처리
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "users/login";
	}
	
}
