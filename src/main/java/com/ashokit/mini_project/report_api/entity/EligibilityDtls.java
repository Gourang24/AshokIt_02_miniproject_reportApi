package com.ashokit.mini_project.report_api.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import lombok.Data;

@Entity
@Data
@Table(name = "Elig_Dtls")
public class EligibilityDtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer eligId;
	private String name;
	private Long mobile;
	private String email;
	private char gender;
	private Long ssn;
	private String planName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
//	@Column(name = "CREATED_BY")
	private String createdBy;

//	@Column(name = "UPDATED_BY")
	private String updatedBy;

//	@CreationTimestamp
//	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDate createdDate;

//	@Column(name = "UPDATED_DATE", insertable = false)
//	@UpdateTimestamp
	private LocalDate updateDate;

}
