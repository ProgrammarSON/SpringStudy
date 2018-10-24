package com.company.hellospring;

import java.util.List;

public interface UserService {
	
	//등록
	public int insertUser(UserDTO dto);
	//수정
	public int updateUser(UserDTO dto);
	//삭제(회원탈퇴)
	public int deleteUser(UserDTO dto);
	//단건조회
	public UserDTO getUser(UserDTO dto);
	//전체조회
	public List<UserDTO> getUsers(UserSearchDTO searchDto);
	public int getCnt(UserSearchDTO searchDto);
}
