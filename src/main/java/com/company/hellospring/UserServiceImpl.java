package com.company.hellospring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.hellospring.common.LogAdvice;

@Service	//빈등록
public class UserServiceImpl implements UserService{

	LogAdvice logAdvice = new LogAdvice();
	//DI
	//@Autowired UserDAO dao;
	
	//@Autowired UserDAOSpring dao;
	
	@Autowired UserDAOMybatis dao;
	@Override
	public int insertUser(UserDTO dto) {
		//dao.insertUser(dto);	//일부러 에러 내기
		return dao.insertUser(dto);
		//트랜젝션 처리가 되었을경우 에러시 rollback 성공시 commit
	}

	@Override
	public UserDTO getUser(UserDTO dto) {
		//logAdvice.printLog();	//횡단관심
		System.out.println("getUser 이다이다");
		return dao.getUser(dto);
	}

	@Override
	public List<UserDTO> getUsers() {
		//logAdvice.printLogging();	//횡단관심
		System.out.println("사용자 목록 조회임");
		//int a = 5/0;			//exception 보기를 위한 일부러 오류 처리 한것
		return dao.getUsers();	//핵심관심
	}

	@Override
	public int updateUser(UserDTO dto) {
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(UserDTO dto) {
		return dao.deleteUser(dto);
	}
	
}
