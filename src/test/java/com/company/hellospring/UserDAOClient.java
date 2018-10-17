package com.company.hellospring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDAOClient {
	
	//UserDAO 테스트
	UserDAO dao;
	UserDTO dto;
	@Before
	public void before() {
		dao = new UserDAO();
		dto = new UserDTO();
		dto.setId("user1");
		dto.setPassword("1111");
	}
	
	@Ignore @Test	
	public void insertTest() {
		assertEquals(1, dao.insertUser(dto));
	}
	
	@Test
	public void getUserTest() {
		assertEquals("user1", dao.getUser(dto).getPassword());	//아이디의 비밀번호가 일치하는 확인
	}
	
	@Test
	public void getUsersTest() {
		assertEquals("doit",dao.getUsers().get(0).getId());	//아이디의 비밀번호가 일치하는 확인
		//assertNotNull(dao.getUsers().get(0).getID()); //null이 아닌지 확인
	}
}
