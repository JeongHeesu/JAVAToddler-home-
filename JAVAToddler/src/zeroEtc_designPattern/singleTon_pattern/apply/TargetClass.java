package zeroEtc_designPattern.singleTon_pattern.apply;


/**
 * 어플리케이션 내 해당 클래스의 인스턴스는 단 하나로 제한하여 메모리 릭 현상을 제거함.
 */
public class TargetClass {
	private static TargetClass target = null;
	
	// 외부에서의 인스턴스화 작업을 막기위해 생성자의 접근지정자 설정을 private으로 설정.
	private TargetClass(){}
	
	// 해당 클래스의 인스턴스 제공을위한 함수를 외부에 제공.
	public static TargetClass getInstance(){
		// 인스턴스화되어있지 않을때만 인스턴스화 수행
		if(target == null){
			target = new TargetClass();
		}
		System.out.println("TargetClass 인스턴스 제공");
		return target;
	}
}
