package com.company.hellospring.websocket;

import java.io.IOException;

import com.company.hellospring.board.CommentsVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SplitTest {
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		String src = "{" + 
						" \"seq\"		: \"2\"," + 
						" \"name\"		: \"홍길동\"," + 
						" \"content\"	: \"수정테스트\"," + 
						" \"boardSeq\"	: \"2\"," + 
						" \"regdate\"	: \"2018-11-06 11:37:45.0\"," + 
						" \"pageUnit\"	: 0" + 
					 " }";
		
		//string -> object		=== requestbody
		ObjectMapper mapper = new ObjectMapper();
		CommentsVO vo = mapper.readValue(src, CommentsVO.class);
		System.out.println("========= string -> object ==========");
		System.out.println(vo);
		//mapper.readValue(, valueType)
		
		//object -> string(JSON) === responsebody
		String des = mapper.writeValueAsString(vo);
		System.out.println("========= object -> string(JSON) ==========");
		System.out.println(des);
		
	}
}
