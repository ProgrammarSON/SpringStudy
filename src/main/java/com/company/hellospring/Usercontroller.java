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
	
	@RequestMapping("/updateUsersForm.do")
	public String updateUsersForm(Model model, UserDTO dto) {
		model.addAttribute("user", userService.getUser(dto));
		return "users/updateUsers";
	}
}
