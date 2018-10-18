package com.company.hellospring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service	//빈등록
public class UserServiceImpl implements UserService{

	LogAdvice logAdvice = new LogAdvice();
	//DI
	@Autowired UserDAO dao;
	
	@Override
	public int insertUser(UserDTO dto) {
		return dao.insertUser(dto);
	}

	@Override
	public UserDTO getUser(UserDTO dto) {
		//logAdvice.printLog();	//횡단관심
		System.out.println("getUser 이다이다");
		return dao.getUser(dto);
	}

	@Override
	public List<UserDTO> getUsers() {
		//logAdvice.printLog();	//횡단관심
		System.out.println("getUsers 임");
		return dao.getUsers();	//핵심관심
	}

	@Override
	public int updateUser(UserDTO dto) {
		return 0;
	}

	@Override
	public int deleteUser(UserDTO dto) {
		return 0;
	}
	
}
