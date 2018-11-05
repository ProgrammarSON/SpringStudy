package com.company.hellospring.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CmmController {
	/**
	* validato rule dynamic Javascript 서버에서 유효성 검사
	*/
	@RequestMapping("/validator.do")
		public String validate(){
		return "popup/cmm/validator";
	}		
}
