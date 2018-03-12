package zeroEtc_designPattern.factory_method_pattern.apply;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoSil204 implements IHoSil {

	@Override
	public void entrance() {
		System.out.println("204호 입장합니다.");
	}

	@Override
	public boolean undergoAnInspection(String topic) {
		System.out.println(topic + "으로 점검을 받다");
		return true;
	}

	@Override
	public boolean subjectToAudit(Date date) {
		System.out.println(new SimpleDateFormat("yyyy.MM.dd") + "에 감사를 받다");
		return true;
	}
}
