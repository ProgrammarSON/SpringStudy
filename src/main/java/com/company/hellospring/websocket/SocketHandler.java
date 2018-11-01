package com.company.hellospring.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.company.hellospring.UserDTO;
import com.company.hellospring.UserSearchDTO;
import com.company.hellospring.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class SocketHandler extends TextWebSocketHandler implements InitializingBean {
	private final Logger logger = LogManager.getLogger(getClass());
	private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
	
	@Resource
	private UserService userService;

	public SocketHandler() {
		super();
		this.logger.info("create SocketHandler instance!");
	}

	@Override
	// onClose
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		sessionSet.remove(session);
		this.logger.info("remove session!");
	}

	@Override
	//onOpen
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);
		sessionSet.add(session);
		this.logger.info("add session!");
	}

	@Override
	// onMessage
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
		this.logger.info("receive message:" + message.toString()); // json string을 vo로 변환
		//json string - > java object
		ObjectMapper mapper = new ObjectMapper();
		MsgVO msgvo = mapper.readValue((String) message.getPayload(), MsgVO.class);
	
		//전송 결과
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cmd",msgvo.getCmd());
		
		if(msgvo.getCmd().equals("get")) {
			UserDTO dto = new UserDTO();
			dto.setId(msgvo.getMsg());
			UserDTO result = userService.getUser(dto);
			//java object -> json string
			
			map.put("msg",result);
			sendMessage(mapper.writeValueAsString(map));
			
		}else if (msgvo.getCmd().equals("all")) {
			UserSearchDTO searchDto = new UserSearchDTO ();
			//searchDto.setSearchKeyword(msgvo.getMsg());
			searchDto.setStart(1);
			searchDto.setEnd(10);
			List<UserDTO> result = userService.getUsers(searchDto);
			map.put("msg", result);
				
			sendMessage(mapper.writeValueAsString(map));
		}		
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		this.logger.error("web socket error!", exception);
	}

	@Override
	public boolean supportsPartialMessages() {
		this.logger.info("call method!");
		return super.supportsPartialMessages();
	}

	public void sendMessage(String message) {
		for (WebSocketSession session : this.sessionSet) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (Exception ignored) {
					this.logger.error("fail to send message!", ignored);
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Thread thread = new Thread() {
			int i = 0;

			@Override
			public void run() {
				while (true) {
					try {
						sendMessage("send message index " + i++);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		//thread.start();
	}
}
