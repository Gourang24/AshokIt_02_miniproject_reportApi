package com.ashokit.mini_project.report_api.service;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.mini_project.report_api.entity.EligibilityDtls;
import com.ashokit.mini_project.report_api.repo.EligibilityDtlsRepo;
import com.ashokit.mini_project.report_api.request.SearchRequest;
import com.ashokit.mini_project.report_api.response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private EligibilityDtlsRepo eliRepo;

	@Override
	public List<String> getUniquePlanNames() {
		List<String> uniquePlanNames = eliRepo.getUniquePlanNames();
		return uniquePlanNames;

	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return eliRepo.getUniquePlanStatuses();

	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		List<SearchResponse> response = new ArrayList<>();
		EligibilityDtls queryBuilder = new EligibilityDtls();
		if (request.getPlanName() != "null" && request.getPlanName() != "") {
			queryBuilder.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != "null" && request.getPlanStatus() != "") {
			queryBuilder.setPlanStatus(request.getPlanStatus());
		}
		if (request.getPlanStartDate().toString() != "null" && request.getPlanStartDate().toString() != "") {
			queryBuilder.setPlanStartDate(request.getPlanStartDate());
		}
		if (request.getPlanEndDate().toString() != "null" && request.getPlanEndDate().toString() != "") {
			queryBuilder.setPlanEndDate(request.getPlanEndDate());
		}

		Example<EligibilityDtls> example = Example.of(queryBuilder);

		List<EligibilityDtls> entities = eliRepo.findAll(example);
		for (EligibilityDtls entity : entities) {
			SearchResponse sr = new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}
		return response;

	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {
		List<EligibilityDtls> entities = eliRepo.findAll();

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Phno");
		headerRow.createCell(3).setCellValue("Gender");
		headerRow.createCell(4).setCellValue("SSN");

		for (EligibilityDtls entity : entities) {
			int i = 1;
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getEmail());
			dataRow.createCell(2).setCellValue(entity.getMobile());
			dataRow.createCell(3).setCellValue(entity.getGender());
			dataRow.createCell(4).setCellValue(entity.getSsn());

			i++;

		}
		ServletOutputStream outputStream = response.getOutputStream();

		workbook.write(outputStream);
		// closing the Stream

		// closing the workbook
		workbook.close();
		outputStream.close();

	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		List<EligibilityDtls> entities = eliRepo.findAll();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Search Data", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

//		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Phno", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		for (EligibilityDtls entity : entities) {
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}
		document.add(table);
		document.close();
	}

}
