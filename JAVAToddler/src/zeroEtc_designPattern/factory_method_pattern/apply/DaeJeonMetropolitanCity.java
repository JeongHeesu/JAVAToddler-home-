package zeroEtc_designPattern.factory_method_pattern.apply;

import java.util.Date;

public interface DaeJeonMetropolitanCity {
	public String INVESTMENT_NAME = "대전광역시 S/W지역인재육성사업";
	
	//점검하다
	public boolean undergoAnInspection(String topic);
	//감사하다
	public boolean subjectToAudit(Date date);
}
