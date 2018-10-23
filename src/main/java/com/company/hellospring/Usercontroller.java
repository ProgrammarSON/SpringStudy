package com.company.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Usercontroller {
	
	@Autowired	//DI(Dependency Injection)
	UserService userService;
	/*	@Autowired
	UserDAO userDAO;
*/	
	@RequestMapping("/getUsers.do")
	public String getUsers(Model model) {
		model.addAttribute("list",userService.getUsers());
		return "users/getUsers";
	}
	
	//수정폼
	@RequestMapping("/updateUsersForm.do")
	public String updateUsersForm(Model model, UserDTO dto) {
		model.addAttribute("user", userService.getUser(dto));
		return "users/updateUsers";
	}
	
	//수정처리
	@RequestMapping("/updateUser.do")
	public String updateUser(Model model, UserDTO dto) {
		model.addAttribute("user", userService.updateUser(dto));
		return "redirect:/getUsers.do";
	}
	
	//등록폼
	@RequestMapping("/insertUserForm.do")
	public String insertUsersForm(Model model, UserDTO dto) {
		return "users/insertUser";
	}
	
	//등록처리
	@RequestMapping("/insertUser.do")
	public String insertUser(Model model, UserDTO dto) {
		model.addAttribute("user", userService.insertUser(dto));
		return "redirect:/getUsers.do";
	}
}
