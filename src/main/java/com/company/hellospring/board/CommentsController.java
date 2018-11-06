package com.company.hellospring.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class CommentsController {

	//서비스 injection
	@Autowired
	CommentsService commentService;
	
	//댓글 등록
	@RequestMapping("insertComments")
	@ResponseBody
	public CommentsVO insertComments(CommentsVO vo){
		commentService.insertComments(vo);
		return commentService.getComments(vo);
	}
	//댓글 수정
	@RequestMapping("updateComments")
	@ResponseBody
	public CommentsVO updateComments(CommentsVO vo){
		commentService.updateComments(vo);
		return vo;
	}
	//댓글 삭제
	@RequestMapping("deleteComments")
	@ResponseBody
	public CommentsVO deleteComments(CommentsVO vo){
		commentService.deleteComments(vo);
		return vo;
	}
	//댓글 목록
	@RequestMapping("getCommentsList")
	@ResponseBody
	public List<CommentsVO> getCommentsList(CommentsVO vo){
		vo.setPageUnit(10);
		return commentService.getCommentsList(vo);
	}
}
