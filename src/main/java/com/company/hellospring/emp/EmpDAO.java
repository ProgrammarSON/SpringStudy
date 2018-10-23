package com.company.hellospring.emp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	@Autowired SqlSessionTemplate mybatis;
	
	/*
		GRANT SELECT ON employees to spring;
		GRANT SELECT ON departments to spring;
		GRANT ALL ON departments to spring;
	 */
	public int getCnt() {
		return mybatis.selectOne("emp.getCnt");
	}
	
	public List<EmpDTO> getSalaryTop3(){
		return mybatis.selectList("emp.getSalaryTop3");
	}
	
	public List<EmpDTO> getEmps(EmpDTO dto){
		return mybatis.selectList("emp.getEmps",dto);
	}
	
	public List<HashMap<String,Object>> getEmpDept(){
		return mybatis.selectList("emp.getEmpDept");
	}
	
	public int insertEmp(EmpDTO dto) {
		int r = mybatis.insert("emp.insertEmp",dto);
		System.out.println(r);
		return r;
	}
}
