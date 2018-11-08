package com.company.hellospring.board;

import java.util.List;

public interface BoardService {
	
	//등록
	public void insertBoard(BoardDTO dto);
	//수정
	public void updateBoard(BoardDTO dto);
	//삭제
	public void deleteBoard(BoardDTO dto);
	//단건조회
	public BoardDTO getBoard(BoardDTO dto);
	//전체조회
	public List<BoardDTO> getBoards();
}
