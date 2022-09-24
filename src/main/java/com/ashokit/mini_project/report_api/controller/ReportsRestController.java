package com.ashokit.mini_project.report_api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.mini_project.report_api.request.SearchRequest;
import com.ashokit.mini_project.report_api.response.SearchResponse;
import com.ashokit.mini_project.report_api.service.ReportsService;

@RestController
public class ReportsRestController {
@Autowired
private ReportsService service;
	
	
	@GetMapping("/pdf")
    public void exportToPDF(HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Data.pdf";
        response.setHeader(headerKey, headerValue);
       this.service.generatePdf(response);
	}
	
	@GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Data.xls";
        response.setHeader(headerKey, headerValue);
       this.service.generateExcel(response);
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request){
		List<SearchResponse> search = service.search(request);
		return new ResponseEntity<>(search,HttpStatus.OK);
	}
	
	@GetMapping("/pstatus")
	public ResponseEntity<List<String>> getUniquePlanStatuses(){
		 List<String> uniquePlanStatuses = service.getUniquePlanStatuses();
		return new ResponseEntity<>(uniquePlanStatuses,HttpStatus.OK);
	}
	
	@GetMapping("/pname")
	public ResponseEntity<List<String>> getUniquePlans(){
		List<String> uniquePlanNames = service.getUniquePlanNames();
		return new ResponseEntity<>(uniquePlanNames,HttpStatus.OK);
	}
}
