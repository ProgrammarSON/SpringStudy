package com.company.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.hellospring.common.Paging;

@Controller
public class Usercontroller {
	
	@Autowired	//DI(Dependency Injection)
	UserService userService;
	/*	@Autowired
	UserDAO userDAO;
*/	
	@RequestMapping("/getUsers.do")
	public String getUsers(Model model 
						   ,UserSearchDTO searchDto
						   ,Paging paging) {
		//조회할 레코드 건수
		paging.setPageUnit(3); //3건식 페이징 처리
		
		// 현재페이지번호 . 없으면 1page로 설정
		if(paging.getPage() == null) {
			paging.setPage(1);
		}
		
		//전체건수
		int total= userService.getCnt(searchDto);
		paging.setTotalRecord(total);
		model.addAttribute("paging",paging);
		
		//시작 /마지막 레코드 번호
		searchDto.setStart(paging.getFirst());
		searchDto.setEnd(paging.getLast());
		model.addAttribute("list",userService.getUsers(searchDto));
			
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
