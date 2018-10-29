package com.company.hellospring.board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/getBoards.do")
	public String getUsers(Model model) {
		model.addAttribute("list",boardService.getBoards());
		return "board/getBoards";
	}
	
	//등록폼
	@RequestMapping(value="insertBoard", method=RequestMethod.GET)
	public String insertBoardForm() {
		return "board/insertBoard";
	}
	
	//등록처리
	@RequestMapping(value="insertBoard", method=RequestMethod.POST)
	public String insertBoard(BoardDTO board
							  ,HttpServletRequest request) throws IllegalStateException, IOException {
		
		//String folder = request.getSession().getServletContext().getRealPath("/images");
		
		//첨부파일 처리
		MultipartFile uploadFile = board.getUploadFile();
			
		/*다중파일 업로드시*/
/*		MultipartFile[] uploadFile = board.getUploadFile();
		String filename = "";
		StringBuffer temp = new StringBuffer();
		for(int i=0; i<uploadFile.length; i++) {
			if(!uploadFile[i].isEmpty() && uploadFile[i].getSize() > 0) {
				//filename = new String(uploadFile[i].getOriginalFilename().getBytes("8859_1"),"UTF-8");	
				//8859_1 번호
				//브라우저에서 8859_1로 보냄으로 이것을 읽은다음 UTF-8로 인코딩을 한다.
				filename = uploadFile[i].getOriginalFilename();
				//uploadFile[i].transferTo(new File("c:/upload",filename));			
				uploadFile[i].transferTo(new File(folder ,filename));
				if( i == uploadFile.length-1 )
					temp.append(filename); 
				else
					temp.append(filename + "||"); 
				
			}
		}
		board.setUploadFileName(temp.toString());*/
		
		//파일명 중복 처리(FileRenamePolicy 추가 할것);	
		if(!uploadFile.isEmpty() && uploadFile.getSize() > 0) {
			//String filename = new String(uploadFile.getOriginalFilename().getBytes("8859_1"),"UTF-8");	
			/*			8859_1 번호
				브라우저에서 8859_1로 보냄으로 이것을 읽은다음 UTF-8로 인코딩을 한다.*/
			String filename = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("c:/upload",filename));			
			board.setUploadFileName(filename);
		}
		
		//게시글 등록
		boardService.insertBoard(board);
		return "redirect:/getBoards.do";
	}
	
	@RequestMapping(value = "/FileDown.do")
	public void cvplFileDownload(@RequestParam Map<String, Object> commandMap, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String atchFileId = (String) commandMap.get("atchFileId");
		File uFile = new File("c:/upload", atchFileId);
		long fSize = uFile.length();
		if (fSize > 0) {
			String mimetype = "application/x-msdownload";
			response.setContentType(mimetype);
			// filename=\"" + URLEncoder.encode(fvo.getOrignlFileNm(), "utf-8") + "\"");
			setDisposition(atchFileId, request, response);
			BufferedInputStream in = null;
			BufferedOutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(uFile));
				out = new BufferedOutputStream(response.getOutputStream());
				FileCopyUtils.copy(in, out);
				out.flush();
			} catch (IOException ex) {
			} finally {
				in.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} else {
			response.setContentType("application/x-msdownload");
			PrintWriter printwriter = response.getWriter();
			printwriter.println("<html>");
			printwriter.println("<h2>Could not get file name:<br>" + atchFileId + "</h2>");
			printwriter.println("<center><h3><a href='javascript: history.go(-1)'>Back</a></h3></center>");
			printwriter.println("&copy; webAccess");
			printwriter.println("</html>");

			printwriter.flush();
			printwriter.close();
		}
	}
		
	
	private String getBrowser(HttpServletRequest request) {
		String header = request.getHeader("User-Agent");
		if (header.indexOf("MSIE") > -1) {
		return "MSIE";
		} else if (header.indexOf("Trident") > -1) { // IE11 문자열 깨짐 방지
		return "Trident";
		} else if (header.indexOf("Chrome") > -1) {
		return "Chrome";
		} else if (header.indexOf("Opera") > -1) {
		return "Opera";
		}
		return "Firefox";
	}
	
	private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String browser = getBrowser(request);
		String dispositionPrefix = "attachment; filename=";
		String encodedFilename = null;
		if (browser.equals("MSIE")) {
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Trident")) { // IE11 문자열 깨짐 방지
			encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
		} else if (browser.equals("Firefox")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Opera")) {
			encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
		} else if (browser.equals("Chrome")) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, "UTF-8"));
				} else {
					sb.append(c);
				}
			}
			encodedFilename = sb.toString();
		} else {
			throw new IOException("Not supported browser");
		}
		response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
		if ("Opera".equals(browser)) {
			response.setContentType("application/octet-stream;charset=UTF-8");
		}
	}
	
}
