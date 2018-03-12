package zeroEtc_designPattern.factory_method_pattern.apply;

import java.util.Date;

public interface MistryOfEmploymentAndLabor {
	public String INVESTMENT_NAME = "국가기간 전략산업 집중 육성 사업";
	
	//점검하다
	public boolean undergoAnInspection(String topic);
	//감사하다
	public boolean subjectToAudit(Date date);
}
