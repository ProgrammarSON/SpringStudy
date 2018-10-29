package com.company.hellospring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.hellospring.board.BoardDAO;
import com.company.hellospring.board.BoardDTO;
import com.company.hellospring.emp.EmpDAO;
import com.company.hellospring.emp.EmpDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:servlet-context-test.xml")
public class EmpDAOClient {
	
	@Autowired EmpDAO dao;
	@Autowired BoardDAO boarddao;
	@Test
	public void getCnt() {
		//System.out.println("전체 건수: "+dao.getCnt());
/*		List<EmpDTO> list = dao.getSalaryTop3();
		for(EmpDTO e: list) {
			System.out.println(e.getEmployeeId() + ":" +
								e.getSalary());
		}*/
	}
	
	@Test
	public void getEmps() {
/*		EmpDTO dto = new EmpDTO();
		dto.setFirstName("Steven");
		//dto.setDepartmentId("110");
		List<EmpDTO> list = dao.getEmps(dto);
		System.out.println("조회건수: " + list.size());
		for(EmpDTO e : list) {
			System.out.println(e);
		}*/
	}
	
	@Test
	public void getEmpDept() {
/*		List<HashMap<String,Object>> list = dao.getEmpDept();
		for(int i=0; i<list.size(); i++) {
			HashMap<String,Object> map =
					(HashMap<String, Object>) list.get(i);
			System.out.println(map.get("deptName")+":"+
								map.get("fname"));
			
		}*/
	}
	
	@Test
	public void insertEmp() {
/*		EmpDTO dto = new EmpDTO();
		dto.setLastName("헤이");
		dto.setEmail("a@b.ccccc");
		dto.setHireDate("2018/01/01");
		dto.setJobId("IT_PROG");
		dao.insertEmp(dto);
		System.out.println("등록 사원번호 : "+dto.getEmployeeId());*/
	}
	
	//게시글 등록
	@Test
	public void insertBoardProc() {
		BoardDTO dto = new BoardDTO();
		dto.setTitle("프로시저 테스트 2");
		dto.setWriter("홍길동동");
		dto.setContent("아버지가 아버지가 아님");
		boarddao.insertBoardProc(dto);
		System.out.println(dto.getSeq());
		System.out.println(dto.getOut_msg());
	}
}
