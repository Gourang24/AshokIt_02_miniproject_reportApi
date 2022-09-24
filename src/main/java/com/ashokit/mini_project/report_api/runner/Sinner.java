
package com.ashokit.mini_project.report_api.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ashokit.mini_project.report_api.entity.EligibilityDtls;
import com.ashokit.mini_project.report_api.repo.EligibilityDtlsRepo;

@Component
public class Sinner implements ApplicationRunner {

	@Autowired
	private EligibilityDtlsRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		EligibilityDtls o1 = new EligibilityDtls();
//	o1.setEligId(1);
		o1.setName("Gourang");
		o1.setEmail("abc@gmail.com");
		o1.setGender('M');
		o1.setMobile((long) 987456321);
		o1.setSsn((long) 684874679);
		o1.setPlanName("SSAS");
		repo.save(o1);

		EligibilityDtls o2 = new EligibilityDtls();
//		o1.setEligId(2);
		o2.setName("Aman");
		o2.setEmail("asd@gmail.com");
		o2.setGender('M');
		o2.setMobile((long) 88999);
		o2.setSsn((long) 684874679);
		o2.setPlanName("PAAS");
		repo.save(o2);

	}

}
