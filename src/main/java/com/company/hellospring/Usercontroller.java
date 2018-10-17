package com.company.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Usercontroller {
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/getUsers.do")
	public String getUsers(Model model) {
		model.addAttribute("list",userDAO.getUsers());
		return "users/getUsers";
	}
	
	@RequestMapping("/updateUsersForm.do")
	public String updateUsersForm(Model model, UserDTO dto) {
		model.addAttribute("user", userDAO.getUser(dto));
		return "users/updateUsers";
	}
}
