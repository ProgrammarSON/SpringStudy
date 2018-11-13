package com.company.hellospring.board;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOJPA {
	@PersistenceContext
	private EntityManager em;
	
	public void insertBoard(BoardDTO dto) {
		System.out.println("===> JPA로 insertBoard() 기능 처리");
		em.persist(dto);
	}

	public void updateBoard(BoardDTO dto) {
		System.out.println("===> JPA로 updateBoard() 기능 처리");
		em.merge(dto);
	}

	public void deleteBoard(BoardDTO dto) {
		System.out.println("===> JPA로 deleteBoard() 기능 처리");
		em.remove(em.find(BoardDTO.class, dto.getSeq())); 
	}

	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("===> JPA로 getBoard() 기능 처리");
		return (BoardDTO) em.find(BoardDTO.class, dto.getSeq());
	}
	
	public List<BoardDTO> getBoardList(){
		System.out.println("===> JPA로 getBoardList() 기능 처리");
		return em.createQuery("from BoardDTO b ORDER BY b.seq desc").getResultList();//일반 sql문과 별개
		
	}
	
	/*
	public  List<BoardDTO> getBoardList(BoardDTO vo) {
		System.out.println("===> JPA로 getBoardList() 검색 기능 처리");
		//return em.createQuery("from BoardVO b order by b.seq desc").getResultList();	
		TypedQuery<BoardDTO> query ;
		if ("title".equals(vo.getSearchCondition()))
			query = em.createQuery("from BoardVO b  where b.title = :title order by b.seq desc", BoardVO.class);
		else if  ("content".equals(vo.getSearchCondition()))
			query = em.createQuery("from BoardVO b  where b.content = :title order by b.seq desc", BoardVO.class);
		else 
			query = em.createQuery("from BoardVO b  order by b.seq desc", BoardVO.class);
		if(vo.getSearchCondition() != null && !vo.getSearchCondition().equals(""))
			query.setParameter("title", vo.getSearchKeyword());	// == pstmt.setString
		query.setFirstResult(vo.getFirst()-1);	//paging 처리시 first ~ max까지 검색
		query.setMaxResults(vo.getLast());
		return query.getResultList();
	}*/
	
	public int getCount(BoardDTO dto) {
		System.out.println("===> JPA로 getCount() 기능 처리");
		return ((Long)em.createQuery("select count(b) from BoardVO b")
				.getSingleResult()).intValue();
	}

}
