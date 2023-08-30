package mypackage.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mypackage.model.Note;
import mypackage.model.Secret;
import mypackage.repository.SecretRepository;
import mypackage.response.HttpStatusCode;
import mypackage.util.ValidateUtil;

@Log4j2
@Service //have to be used here also
public class SecretServiceImpl //impl means implementation //@Transaction, validation and complex operations takes place here
{	
	@Autowired
	SecretRepository secretRepo;
	
	//private static JasperDesign design = null;
	//private static JasperReport report = null;
	
	public void update(Secret secret)
	{
		log.info("SecretServiceImpl -> update -> started");
		String un = secret.getUn();
		String pwd = secret.getPwd();
		
		String regex1 = "^[a-z\\d]+$";
		String regex2 = "^[\\w@-]+$";
		
		//un
		ValidateUtil.throwExcIfNull(un, HttpStatusCode.NULL);
		secret.setUn(un.trim()); //server should be intact and flexible for all type of clients
		ValidateUtil.checkLimit(un, 6, 10, HttpStatusCode.LIMIT);
		ValidateUtil.checkPattern(un, regex1, HttpStatusCode.PATTERN);
		
		//pwd
		ValidateUtil.throwExcIfNull(pwd, HttpStatusCode.NULL);
		secret.setPwd(pwd.trim());
		ValidateUtil.checkLimit(pwd, 6, 10, HttpStatusCode.LIMIT);
		ValidateUtil.checkPattern(pwd, regex2, HttpStatusCode.PATTERN);	
		log.info("SecretServiceImpl -> update -> ended");
	}
	
	public void downloadExcel(Long id, HttpServletResponse hsr) throws Exception //throws IOException, Exception //can also be used because IOException is thrown mainly and Exception is used to catch all uncaught exceptions //throwned exceptions are catched in service catch block
	{
		log.info("SecretServiceImpl -> downloadExcel -> started");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Secret");
		
		String[] columns = {"S.No", "Message"};
		
		Font font = wb.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLUE.getIndex());
		
		CellStyle style = wb.createCellStyle();
		style.setFont(font);
		
		//Creating first row headings only
		Row row = sheet.createRow(0);
		
		for(int i=0;i<columns.length;i++)
		{
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(style);
		}
		
		Secret secret = secretRepo.getOne(id);
		
		int rowNum = 1;
		for(Note note : secret.getNotes())
		{
			row.createCell(0).setCellValue(rowNum);
			row.createCell(1).setCellValue(note.getMsg());
			rowNum++;
		}
		hsr.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		hsr.setHeader("Content-Disposition", "attachment ;filename="+"Secret"+".xlsx");
		
		ServletOutputStream sos = null;
		//throws IOException
	    sos = hsr.getOutputStream();
		wb.write(sos);
		sos.flush();
		sos.close();
		wb.close();
		//throws IOException
		log.info("SecretServiceImpl -> downloadExcel -> ended");
	}
	
	/*public void uploadExcel(Long id, byte[] file) throws Exception
	{
	    log.info("SecretServiceImpl -> uploadExcel -> started");
        InputStream is = new ByteArrayInputStream(file);
		
		XSSFWorkbook wb = null;
	    wb = new XSSFWorkbook(is); //throws IOException
	    
		XSSFSheet sheet = wb != null?wb.getSheetAt(0):null;
		
		Secret secret = secretRepo.getOne(id);
		List<Note> notes = new ArrayList<Note>();
		
		int totalRow = sheet.getLastRowNum();
		for(int i=1;i<totalRow;i++)
		{
			Note note = new Note();
			String noteMsg = sheet.getRow(i).getCell(1).getStringCellValue();
			note.setMsg(noteMsg);
			notes.add(note);
		}
		secret.setNotes(notes);
		secretRepo.save(secret);
		wb.close(); //throws IOException
		log.info("SecretServiceImpl -> uploadExcel -> ended");
	}*/
	
	/*public byte[] generatePdf(Long id) throws Exception
	{
	    log.info("SecretServiceImpl -> generatePdf -> started");
		ClassPathResource cpr = new ClassPathResource("Secret.jrxml");
		InputStream is = cpr.getInputStream();
		
		design = JRXmlLoader.load(is);
		report = JasperCompileManager.compileReport(design);
		
		final Map<String, Object> params = new HashMap<String, Object>();
		
		Secret secret = secretRepo.getOne(id);
		List<Secret> data = new ArrayList<Secret>();
		data.add(secret);
		
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
		final JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
		
		log.info("SecretServiceImpl -> generatePdf -> ended");
		return JasperExportManager.exportReportToPdf(print);
	}*/
}