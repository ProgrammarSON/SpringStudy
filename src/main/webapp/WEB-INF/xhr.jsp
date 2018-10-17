<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- !DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" 사이트에서 정의된 태그만 사용가능 =html 4버전 (section 태그, ... 사용불가능)  --> 
<!DOCTYPE html> <!--  html 5버전 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xhr.jsp</title>
<!--  head가 실행되고 body가 실행됨. (script 수행후에 body 수행) -->
<!--  자바스크립트를 이용한 AJAX 사용  11-40 -->
<script>
	function dupcheck() {
		//1.xhr 객체생성
		var xhr = new XMLHttpRequest();
		//2. callback 함수 이용
		xhr.onreadystatechange = function() {
			if( xhr.readyState == 4 ) // 서버로 응답요청 전송완료
			{
				if(xhr.status == 200 ) // 서버 ->클라이언트 전송 OK
				{
					// 결과처리 ( String, JSON, XML)
					// 1.String형태의 데이터 
					//document.getElementById("result").innerHTML = xhr.responseText; 
					// 2.JSON형태의 데이터
					//var jsondata = eval( "(" + xhr.responseText +")"); 제이슨 타입으로 데이터 변환(이전의 JSON변환방식)
					//var jsondata = JSON.parse(xhr.responseText); // 현재 가장많이사용하는 제이슨형태로 데이터변환하는 방법. String -> object
					//document.getElementById("result").innerHTML = jsondata.userid + ":" + jsondata.result;
					// 3.XML형태의 데이터 처리
					var datas = xhr.responseXML;
					var userid = datas.getElementsByTagName("userid")[0].firstChild.nodeValue; // userid의 text값도 하나의 노드로 인식함.
					var result = datas.getElementsByTagName("result")[0].firstChild.nodeValue;
					document.getElementById("result").innerHTML = "xml: " + userid + ":" + result;
				} else {
					// 에러 메시지 표현
					alert(xhr.status + ":" + xhr.statusText);
				}
			} 	
			else 
			{
					// 로딩중 이미지 표현
					document.getElementById("result").innerHTML = "처리중";
			}
		}
		//3. open 함수 이용 (서버연결)
		//쿼리문자열
		var param = "?userid=" + document.getElementById("userid").value;
		xhr.open("get", "LoginCheck"+param ) // LoginCheck == https://localhost:8443/ajax/LoginCheck?userid=admin3, 로컬이라서 LoginCheck로 적어줌
		//4. send 함수 이용
		xhr.send(); // post 방식일떄 send에 param 들어가야함
	}
</script>
</head>
<body>
<form>
	<input type="text" name="userid" id="userid" onchange="dupcheck()" /><span id="result"></span>
	<input type="password" name="userpw" id="userpw" />
	<input type="submit" value="로그인" />
</form>
</body>
</html>