package com.company.hellospring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOSpring {
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final String USER_LIST = "SELECT * FROM USERS ORDER BY id";
	private final String USER_GET = "SELECT * FROM USERS WHERE id=?";
	private final String USER_INSERT = "INSERT INTO USERS(ID, PASSWORD, NAME, ROLE ) VALUES(?,?,?,?)";
	private final String USER_DELETE = "DELETE FROM USERS WHERE id = ?";
	private final String USER_UPDATE = "UPDATE USERS SET PASSWORD = ?, NAME = ?, ROLE = ? WHERE id = ?";
	
	//추가
	public int insertUser(UserDTO dto) {
		Object[] arr = { dto.getId(), 
						 dto.getPassword(),
						 dto.getName(), 
						 dto.getRole()};
		return jdbcTemplate.update(USER_INSERT,arr);
	/*	return jdbcTemplate.update(USER_INSERT, 
				dto.getId(), dto.getPassword(),dto.getName(), dto.getRole());*/
	}
	//수정
	public int updateUser(UserDTO dto) {
		Object[] arr = { dto.getPassword(),
				 		 dto.getName(), 
				 		 dto.getRole(),
				 		 dto.getId()};
		return jdbcTemplate.update(USER_UPDATE,arr);	
	}
	
	//삭제
	public int deleteUser(UserDTO dto) {
		return jdbcTemplate.update(USER_DELETE,dto.getId());
	}
	
	//단건조회
	public UserDTO getUser(UserDTO dto) {
		Object[] arr = {dto.getId()};
		return jdbcTemplate.queryForObject(USER_GET, arr,new UserRowMapper());
	}
	
	//전체조회
	public List<UserDTO> getUsers(){
		System.out.println("UserDAOSpring 목록조회====");
		return jdbcTemplate.query(USER_LIST, new UserRowMapper());
	}
}

class UserRowMapper implements RowMapper<UserDTO>{

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		UserDTO dto = new UserDTO();
		dto.setId(rs.getString("ID"));
		dto.setName(rs.getString("NAME"));
		dto.setPassword(rs.getString("PASSWORD"));
		dto.setRole(rs.getString("ROLE"));
		return dto;
	}
	
}