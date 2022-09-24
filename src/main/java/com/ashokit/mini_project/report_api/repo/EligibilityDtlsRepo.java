package com.ashokit.mini_project.report_api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.mini_project.report_api.entity.EligibilityDtls;

public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDtls, Integer> {

	@Query("select distinct p.planName from EligibilityDtls p")
	public List<String> getUniquePlanNames();

	@Query("select distinct planStatus from EligibilityDtls")
	public List<String> getUniquePlanStatuses();

}
