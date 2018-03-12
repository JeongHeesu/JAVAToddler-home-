package zeroEtc_designPattern.factory_method_pattern.apply;

// The Powers That Be : 담당자
public abstract class TPTB {
	
	private String positionName;
	private String professorName;
	private String contactNumber;
	private String subjectName;
	
	public TPTB(String positionName, String professorName, String contactNumber,
			String subjectName) {
		this.positionName = positionName;
		this.professorName = professorName;
		this.contactNumber = contactNumber;
		this.subjectName = subjectName;
	}
	
	// 출석 체크
	public abstract boolean morningAttendanceCheck();
	// 저녁 종례
	public abstract boolean eveningAssembly();
	
	public String getPositionName() {
		return positionName;
	}
	public String getProfessorName() {
		return professorName;
	}
	public String getContactNumber() {
		return contactNumber;
	}
}
