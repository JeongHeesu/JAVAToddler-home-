package zeroEtc_designPattern.factory_method_pattern.apply;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoSil201 extends TPTB  implements IHoSil {
	
	public HoSil201(String positionName, String professorName,
			String contactNumber, String subjectName) {
		super(positionName, professorName, contactNumber, subjectName);
	}

	@Override
	public boolean morningAttendanceCheck() {
		System.out.println("201호 아침 출석을 체크합니다.");
		return true;
	}

	@Override
	public boolean eveningAssembly() {
		System.out.println("201호 저녁 종례를 합니다.");
		return true;
	}

	@Override
	public void entrance() {
		System.out.println("201호 입장합니다.");
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
