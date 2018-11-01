<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing websockets</title>
</head>
<body>
	<fieldset>
		<textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
		<br/> <input id="inputMessage" type="text" /> 
		<input type="button" value="send" onclick="send()" />
		<input type="button" value="전체조회" onclick="sendall()">
	</fieldset>
</body>
<script type="text/javascript">
	var textarea = document.getElementById("messageWindow");
	var webSocket = new WebSocket('ws://localhost:8081/spring/springserver.do');
	var inputMessage = document.getElementById('inputMessage');
	webSocket.onerror = function(event) {
		onError(event)
	};
	webSocket.onopen = function(event) {
		onOpen(event)
	};
	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	function onMessage(event) {
		//textarea.value += "상대 : " + event.data + "\n";
		var msgvo = JSON.parse(event.data);
	
		if(msgvo.cmd == "get"){
			textarea.value += msgvo.msg.name + "," + msgvo.msg.role + "\n";
		}else if(msgvo.cmd == "all"){
			
			for( i in msgvo.msg ){
				textarea.value += msgvo.msg[i].name + "," + msgvo.msg[i].role+"\n";
			}
			
			textarea.value += "\n\n"
			for(var i=0; i<msgvo.msg.length; i++){
				textarea.value += msgvo.msg[i].name + "," + msgvo.msg[i].role+"\n";
			}
		}
		
		chatAreaScroll();
	}
	function onOpen(event) {
		textarea.value += "연결 성공\n";
	}
	function onError(event) {
		console.log(event);
		alert(event.data);
	}
	function send() {
		textarea.value += "나 : " + inputMessage.value + "\n";
		
		var v_msg ={
			cmd: "get",
			msg: document.getElementById("inputMessage").value,
			id: "SON"	
		};
		
		
		webSocket.send(JSON.stringify(v_msg));
		inputMessage.value = "";
	}
	
	function sendall(){
		var v_msg ={
				cmd: "all",
				id: "SON"	
		};
		webSocket.send(JSON.stringify(v_msg));
		inputMessage.value = "";
	}
	
	function chatAreaScroll() {
		//using jquery
		var textArea = $('#messageWindow');
		textArea.scrollTop(textArea[0].scrollHeight - textArea.height());
		textArea.scrollTop(textArea[0].scrollHeight);
		//using javascript
		var textarea = document.getElementById('messageWindow');
		textarea.scrollTop = textarea.scrollHeight;
	}
</script>
</html>