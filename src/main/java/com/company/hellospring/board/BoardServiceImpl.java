package com.company.hellospring.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardDAO dao;

	@Override
	public List<BoardDTO> getBoards() {
		// TODO Auto-generated method stub
		return dao.getBoards();
	}

	@Override
	public int insertBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return dao.insertBoard(dto);
	}

	@Override
	public int updateBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO getBoard(BoardDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
