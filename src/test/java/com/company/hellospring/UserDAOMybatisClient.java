package com.company.hellospring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:servlet-context-test.xml")
public class UserDAOMybatisClient {
	
	//UserDAO 테스트
	@Autowired UserDAOMybatis dao;

	@Test
	public void getUserTest() {
		UserSearchDTO searchDto = new UserSearchDTO();
		searchDto.setStart(1);
		searchDto.setEnd(20);
		
		//String[] ids = {"doit", "a", "b"};
		//searchDto.setIds(ids);
		
		searchDto.setSearchCondition("name");
		searchDto.setSearchKeyword("관");
		
		List<UserDTO> list = dao.getUsers(searchDto);
		for(UserDTO dto : list) {
			System.out.println(dto.getId() + ":" +dto.getName());
		}
	}
}
