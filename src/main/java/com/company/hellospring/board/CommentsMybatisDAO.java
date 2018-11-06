package com.company.hellospring.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommentsMybatisDAO {

	@Autowired
	private SqlSessionTemplate sqlSession; 
	
	 public void insertComments(CommentsVO vo){
		 sqlSession.insert("comments.insertComments", vo);
	 }
	 
	 public void updateComments(CommentsVO vo){
		 sqlSession.update("comments.updateComments", vo);
	 }
	 
	 public void deleteComments(CommentsVO vo){
		 sqlSession.delete("comments.deleteComments", vo);
	 }
	 //다건조회
	 public List<CommentsVO> getCommentsList(CommentsVO vo){
		 return sqlSession.selectList("comments.getCommentsList", vo);
	 }
	 //단건조회
	 public CommentsVO getComments(CommentsVO vo){
		 return sqlSession.selectOne("comments.getComments", vo);
	 }
}
