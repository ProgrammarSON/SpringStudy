package com.company.hellospring.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChartController {
	
	@Autowired EmpService empService;
	
	@RequestMapping("/getEmpChart.do")
	@ResponseBody
	public List<Map<String,Object>> getEmpChart(){

		return empService.getEmpChart();
	}
	
}
