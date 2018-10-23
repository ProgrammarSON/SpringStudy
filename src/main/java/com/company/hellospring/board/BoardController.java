package com.company.hellospring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/getBoards.do")
	public String getUsers(Model model) {
		model.addAttribute("list",boardService.getBoards());
		return "board/getBoards";
	}
}
