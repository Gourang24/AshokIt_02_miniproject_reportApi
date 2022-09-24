package com.ashokit.mini_project.report_api.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ashokit.mini_project.report_api.request.SearchRequest;
import com.ashokit.mini_project.report_api.response.SearchResponse;

@Service
public interface ReportsService {
	public List<String> getUniquePlanNames();
	public List<String> getUniquePlanStatuses();
	public List<SearchResponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response) throws Exception;
	
}
