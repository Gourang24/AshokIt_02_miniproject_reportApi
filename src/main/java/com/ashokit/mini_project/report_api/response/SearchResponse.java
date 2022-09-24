package com.ashokit.mini_project.report_api.response;

import lombok.Data;

@Data
public class SearchResponse {

	private String name;
	private String email;
	private Long mobile;
	private char gender;
	private Long ssn;
}
