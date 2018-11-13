package com.company.hellospring.board;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/** JPA 설정 **/
@Entity()
@Table(name="BOARD")
public class BoardDTO {
	
	@Id					//기본키 지정
	@GeneratedValue		//sequnce 지정
	private int seq;
	@Column(name="board_title",nullable=false, length=100)	//DB 컬럼을 board_title로 지정
	private String title;
	@Column(name="board_writer", updatable=false)
	private String writer;
	private String content;
	@Temporal(TemporalType.DATE)	
	private Date regdate = new Date();	//new Date는 초기값 설정을 위해서
	private int cnt;
	private String uploadFileName;
	
	
	/* @Transient는 Table 필드와 무관한것들을 지정 */
	@Transient String out_msg;	
	//MultipartFile uploadFile; //첨부파일	
	@Transient MultipartFile[] uploadFile; //다중 파일시 배열로

	public MultipartFile[] getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile[] uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
/*	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}*/
	
	

	public String getTitle() {
		return title;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

		
	public String getOut_msg() {
		return out_msg;
	}
	public void setOut_msg(String out_msg) {
		this.out_msg = out_msg;
	}
	
	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", regdate=" + regdate + ", cnt=" + cnt + "]";
	}
	
}
