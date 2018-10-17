package com.company.hellospring;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "datas") //bean이 xml로 변환된다고 알려주는 어노테이션. 루트노드명
@XmlAccessorType(XmlAccessType.FIELD) // 자식노드 타입
public class UserBean {
	
	String id;
	@XmlTransient
	String pw; 
	// xml변환할떄 pw필드는 생략하라
	boolean result;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}

	
}
