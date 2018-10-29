package com.company.hellospring.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
	
	@Autowired SqlSessionTemplate mybatis;
	
	public List<BoardDTO> getBoards(){
		System.out.println("board 목록 조회=====");
		return mybatis.selectList("board.getBoards");
	}
	
	public int insertBoardProc(BoardDTO dto) {
		return mybatis.insert("board.insertBoardProc", dto);
	}
	
	public int insertBoard(BoardDTO dto) {
		return mybatis.insert("board.insertBoard", dto);
	}
}
