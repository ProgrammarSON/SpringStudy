package com.company.hellospring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.company.hellospring.common.Paging;

@Controller
@SessionAttributes("user")
public class Usercontroller {
	
	@Autowired	//DI(Dependency Injection)
	UserService userService;
	/*	@Autowired
	UserDAO userDAO;
*/	
	@ModelAttribute("roleMap")	//model.addAttribute("roleMap",map) 이랑 동등
	public Map<String, String> roleMap(){
		Map<String, String> map = new HashMap<String,String>();
		map.put("Admin", "관리자");
		map.put("User", "사용자");
		map.put("ssss", "최고관리자");
		return map;
	}
	//requestMapping 전에 먼저 실행
	
	
	@RequestMapping("/getUsers.do")
	public ModelAndView getUsers(ModelAndView mv 
						   ,UserSearchDTO searchDto
						   ,Paging paging) {
		
		//int a= 5/0;
		//String b= null;
		//b.toString();
		//조회할 레코드 건수
		paging.setPageUnit(3); //3건식 페이징 처리
		
		// 현재페이지번호 . 없으면 1page로 설정
		if(paging.getPage() == null) {
			paging.setPage(1);
		}
		
		//전체건수
		int total= userService.getCnt(searchDto);
		paging.setTotalRecord(total);
		//model.addAttribute("paging",paging);
		mv.addObject("paging",paging);
		//시작 /마지막 레코드 번호
		searchDto.setStart(paging.getFirst());
		searchDto.setEnd(paging.getLast());
		//model.addAttribute("list",userService.getUsers(searchDto));
		mv.addObject("list",userService.getUsers(searchDto));
		
		mv.setViewName("users/getUsers");
		return mv;
	}	
	//수정폼
	@RequestMapping("/updateUsersForm.do/{id}")	//updateUsersForm.do/{id}/{}......
	public String updateUsersForm(Model model, 
								  @PathVariable String id,
								  UserDTO dto) {
		System.out.println("id=========="+id);
		dto.setId(id);  //pathvariable로 인해서 dto에 값이 안들어 왔으므로 셋팅
		model.addAttribute("user", userService.getUser(dto));
		return "users/updateUsers";
	}	
	//수정처리
/*	@RequestMapping("/updateUser.do")
	public String updateUser(Model model, 
							 UserDTO dto,
							 SessionStatus ss) {
		model.addAttribute("user", userService.updateUser(dto));
		ss.setComplete();  			//session complete을 안할시 session attribute에 적용이 안됨
		return "redirect:/getUsers.do";
	}	*/
	
	@RequestMapping("/updateUser.do")
	public String updateUser(Model model, 
							 UserDTO dto,
							 SessionStatus ss) {
		model.addAttribute("user", userService.updateUser(dto));
		ss.setComplete();  			//session complete을 안할시 session attribute에 적용이 안됨
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
	
	@RequestMapping("/getChartData.do")
	@ResponseBody
	public List<Map<String, String>> getChartData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "인사");
		map.put("cnt", "5");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "총무");
		map.put("cnt", "10");
		list.add(map);
		map = new HashMap<String, String>();
		map.put("name", "기획");
		map.put("cnt", "20");
		list.add(map);
		return list;
	}
}
