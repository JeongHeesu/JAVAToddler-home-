package zeroEtc_designPattern.singleTon_pattern.regular;


/**
 * 일반적인 특정 클래스의 인스턴스화
 * 현상 : 동일 클래스의 힙메모리상 인스턴스화를 다수 중복처리한 결과로 메모리가 낭비될수있지 않을까? 메모리 릭.
 */
public class TestMain {

	public static void main(String[] args) {
		TargetClass tc1 = new TargetClass();
		TargetClass tc2 = new TargetClass();
		TargetClass tc3 = new TargetClass();
		TargetClass tc4 = new TargetClass();
		TargetClass tc5 = new TargetClass();
		TargetClass tc6 = new TargetClass();
		TargetClass tc7 = new TargetClass();
	}

}
