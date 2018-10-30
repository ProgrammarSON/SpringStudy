package com.company.hellospring.emp;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmpService {
	public List<Map<String,Object>> getEmpChart();
	
	public List<Map<String,Object>> getEmpDept();

}
