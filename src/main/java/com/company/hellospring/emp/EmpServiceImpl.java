package com.company.hellospring.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired EmpDAO dao;
	
	@Override
	public List<Map<String, Object>> getEmpChart() {
		// TODO Auto-generated method stub
		return dao.getEmpChart();
	}

	@Override
	public List<Map<String, Object>> getEmpDept() {
		// TODO Auto-generated method stub
		return dao.getEmpDept();
	}

	
}
