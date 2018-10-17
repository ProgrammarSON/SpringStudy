package com.company.hellospring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	
	//싱글톤 필요없음
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private void connect() {
		try {
            String user = "spring"; 
            String pw = "spring";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            
            Class.forName("oracle.jdbc.driver.OracleDriver");        
            conn = DriverManager.getConnection(url, user, pw);
            
            System.out.println("Database에 연결되었습니다.\n");
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("DB 드라이버 로딩 실패 :"+cnfe.toString());
        } catch (SQLException sqle) {
            System.out.println("DB 접속실패 : "+sqle.toString());
        } catch (Exception e) {
            System.out.println("Unkonwn error");
            e.printStackTrace();
        }
	}
	
	//등록
	public int insertUser(UserDTO dto) {
		int check=0;	
		//1단계 : connect
		connect();
		//2단계 : statement
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO USERS(ID, PASSWORD, NAME, ROLE ) ");
		sql.append("VALUES(?,?,?,?)");
		
		//3단계 : 실행
		try {						
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getRole());
		//4단계 : 결과처리
			check = pstmt.executeUpdate();			
			System.out.println(check + "건 실행");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
		//5단계 : 연결해제		
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return check;
	}
	
	//단건조회
	public UserDTO getUser(UserDTO dto) {
		UserDTO userDTO = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID, PASSWORD, NAME, ROLE ");
		sql.append("FROM users WHERE id = ?");		
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getString("id"));
				userDTO.setName(rs.getString("name"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setRole(rs.getString("role"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return userDTO;
	}
	
	//전체조회
	public List<UserDTO> getUsers(){
		List<UserDTO> list = null;
		UserDTO dto = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID, PASSWORD, NAME, ROLE ");
		sql.append("FROM USERS ORDER BY ID");
		
		try {
			connect();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			list = new ArrayList();
			
			while(rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getString("ID"));
				dto.setName(rs.getString("NAME"));
				dto.setPassword(rs.getString("PASSWORD"));
				dto.setRole(rs.getString("ROLE"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
		
}
