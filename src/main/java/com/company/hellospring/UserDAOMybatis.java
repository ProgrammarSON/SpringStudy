package com.company.hellospring;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOMybatis {
	
	@Autowired SqlSessionTemplate mybatis;	
	//전체조회
	public List<UserDTO> getUsers(){
		System.out.println("user mybatis 목록 조회=======");
		return mybatis.selectList("user.getUsers");	//sql-map-config.xml에서 설정한 alias user를 따른다.
	}
	//단건조회
	public UserDTO getUser(UserDTO dto) {
		return null;
	}
	//등록
	public int insertUser(UserDTO dto) {
		return 0;
	}
	//수정
	public int updateUser(UserDTO dto) {
		return 0;
	}	
	//삭제
	public int deleteUser(UserDTO dto) {
		return 0;
	}
}
