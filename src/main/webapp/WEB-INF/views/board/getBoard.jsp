<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
$(function(){
	
	var context = '${pageContext.request.contextPath}';	//절대경로 설정
	
	//목록조회 요청
	function loadCommentList() {
		var params = { boardSeq : '${board.seq}' };
		$.getJSON(context + "/getCommentsList",params, function(datas){
			for(i=0; i<datas.length;i++) {
				$("#commentList").append( makeCommentView(datas[i]) );
			}
		});
	}
	
	function makeCommentView(comment){
		var div = $("<div>"); 	// ==document.createElement 태그생성
		//DOM객체를 반환 $()은 무조건 배열 구조
		div.attr("id", "c"+comment.seq);	//<div id="c+comment.seq">
		div.addClass('comment');			//<div id="c+comment.seq" class="comment">
		div[0].comment=comment;  //{id:1,.... }comment Field에 comment 값을 넣음 ,[0] == get(0) 번째는 DOM으로 반환 
								 //자바스크립트는 동적으로 필드 생성 가능
		var str ="<strong class='commentName'>" + comment.name + "</strong>&nbsp&nbsp" 
		        +"<span class='commentContent'>" + comment.content +"</span>&nbsp&nbsp"
				+"<button type=\"button\" class=\"btnUpdFrm\">수정</button>"
				+"<button type=\"button\" class=\"btnDel\">삭제</button>"
		div.html(str);	
		return div;
	}
	
	loadCommentList();	//목록 요청 실행

	//댓글 삭제 이벤트 .btnDel로 생성된 버튼에 모든 이벤트 적용
	$("#commentList").on("click", ".btnDel", function(){
		var seq = $(this).parent().attr("id").substr(1);	//this는 삭제버튼을 가리킨다	
															//substr은 id가 c2일경우 2를 뽑아낸다
														
		if(confirm("삭제할까요?")) {
			var params = "seq="+ seq;  // { seq : seq };
			var url = context + "/deleteComments";
			$.getJSON(url, params, function(datas){
				$('#c'+datas.seq).remove();
			});
		}
	});
	
	
	//댓글 등록 이벤트
	$("#btnAdd").click(function(){
		$("#btnCancel").click();
		var params = $("[name=addForm]").serialize();
		var url = context + "/insertComments";
		$.getJSON(url, params, function(datas){
			$("#commentList").append( makeCommentView(datas) );
			$("[name=addForm]")[0].reset();
		});
	});
	
	//댓글 수정 이벤트
	$("#btnUpd").click(function(){
		var params = $("[name=updateForm]").serialize();
		var url = context + "/updateComments";
		$.getJSON(url, params, function(datas){
			var newDiv = makeCommentView(datas);
			var oldDiv = $("#c"+datas.seq);
			$("#btnCancel").click();
			$(newDiv).replaceAll(oldDiv);  // 수정된 DIV를 교체
		});
	});
	
	//수정폼 이벤트(수정할 댓글밑에 수정폼 보이게 함)
	$("#commentList").on("click", ".btnUpdFrm", function(){
		var seq = $(this).parent().attr("id").substring(1);
		var comment =  $(this).parent().get(0).comment;
		
		//수정할 데이터 입력필드에 셋팅
		$("#updateForm [name=seq]").val(seq);    
		//$("#updateForm [name=name]").val($("#c"+seq+">.commentName").text());	//공백등이 바로 들어갈수도 있음
		$("#updateForm [name=name]").val(comment.name);	//DB에서 바로 읽어낸값
		//$("#updateForm [name=content]").val($("#c"+seq+">.commentContent").text());
		$("#updateForm [name=content]").val(comment.content);
		
		
		//수정할 댓글밑으로 이동하고 보이게
		$("#c"+seq).append($('#commentUpdate'));  
		$('#commentUpdate').show();   
	});
	
	//수정 취소 이벤트
	$("#btnCancel").click(function(){
		$("[name=updateForm]")[0].reset();   //폼 필드 클리어
		$("#comments").append( $("#commentUpdate") );//수정 폼(div)를 이동
		$("#commentUpdate").hide();    // 수정폼 숨기기
	});
	
});
</script>
</head>
<body>
<h3>게시물 단건조회</h3><br>
제목: ${board.title}<br>
작성자: ${board.writer}<br>
내용: ${board.content}<br>
작성일자: ${board.regdate}<br>
<a href="./getBoards.do">목록으로</a> <br><br><hr>

<h4>댓글목록임</h4>
<div id="comments" style="border:1px solid blue">

<div id="commentList"></div>

<!-- 댓글등록시작 -->
<div id="commentAdd">
	<form name="addForm" id="addForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" id="btnAdd"/>
	</form>
</div>
<!-- 댓글등록끝 -->

<!-- 댓글수정폼시작 -->
<div id="commentUpdate" style="display:none">
	<form action="" name="updateForm" id="updateForm">
	<input type="hidden" name="boardSeq" value="${board.seq}">
	<input type="hidden" name="seq" value=""/>
	이름: <input type="text" name="name" size="10"><br/>
	내용: <textarea name="content" cols="20" rows="2"></textarea><br/>
	<input type="button" value="등록" id="btnUpd"/>
	<input type="button" value="취소" id="btnCancel"/>
	</form>
</div>
<!-- 댓글수정폼끝 -->

</div>



</body>
</html>