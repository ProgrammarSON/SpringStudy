package com.company.hellospring;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.company.hellospring.emp.EmpService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Controller
public class ReportController {

	@Autowired EmpService empService;
	@Autowired JavaMailSender emailSender;
	
	//메일 발송
	@RequestMapping("sendMail.do")
	public void sendMail(HttpServletResponse response) throws IOException{
	    SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo("sjw782@naver.com"); 
        message.setSubject("hihihi"); 
        message.setText("hi~ SON Thank you");
        emailSender.send(message);
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.append("<script>")
           .append("alert('메일 발송 성공');")
           .append("location.href='./getUsers.do';")
           .append("</script>");
	}
	
	//엑셀 뷰 리졸버
	@RequestMapping("/deptExcelView.do")
	public ModelAndView excelView(HttpServletResponse response) {
		
		List<Map<String, Object>> list = empService.getEmpDept();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String[] header = {"departmentId","departmentName","managerId"};
		map.put("headers", header);
		map.put("filename", "excel_dept");
		map.put("datas", list);
		return new ModelAndView("commonExcelView", map);
	}
	
	//엑셀출력
	@RequestMapping("/deptExcelCreate.do")
	public void excelCreate(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 엑셀 wookbook 생성
		Workbook wb = new HSSFWorkbook(); // xls 버전
		CellStyle cs = wb.createCellStyle();
		Font f2 = wb.createFont();
		f2.setFontName("궁서체");
		f2.setItalic(true);
		cs.setFont(f2);
		// 시트 추가
		wb.createSheet("first sheet");
		wb.createSheet();
		// 부서목록 출력
		List<Map<String, Object>> list = empService.getEmpDept();
		Row row;
		Cell cell;
		Map<String, Object> map;
		Sheet sheet = wb.getSheetAt(0);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i);
			map = list.get(i);
			Iterator<String> iter = map.keySet().iterator();
			int j = 0;
			while (iter.hasNext()) {
				cell = row.createCell(j++);
				Object field = map.get(iter.next());
				System.out.println(field.getClass());
				if (field instanceof String) {
					cell.setCellValue((String) field);
				} else if (field instanceof BigDecimal) {
					cell.setCellValue(((BigDecimal) field).doubleValue());
				} else if (field instanceof Date) {
					cell.setCellValue((Date) field);
				} else {
					cell.setCellValue(field.toString());
				}
			}
		}
		// 엑셀 파일 저장
		String filename = "c:/Temp/excel_" + System.currentTimeMillis() + ".xls";
		FileOutputStream fos = new FileOutputStream(filename);
		wb.write(fos);
		fos.close();
			
		//다운로드
		File uFile = new File(filename);
		int fSize = (int) uFile.length(); // 파일크기
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(uFile));
		String mimetype = "text/html";
		response.setBufferSize(fSize);
		response.setContentType(mimetype);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		response.setContentLength(fSize);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
		uFile.delete(); // 파일삭제
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	
	
	
	@RequestMapping("report.do")
	public void report(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			JasperReport report = JasperCompileManager
					.compileReport(request.getSession().getServletContext().getRealPath("reports/report3.jrxml"));
			JRDataSource JRdataSource = new JRBeanCollectionDataSource(empService.getEmpDept());
			JasperPrint print = JasperFillManager.fillReport(report, map, JRdataSource);
			JRExporter exporter = new JRPdfExporter();
			OutputStream out;
			response.reset();
			out = response.getOutputStream();
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "report3.pdf");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
