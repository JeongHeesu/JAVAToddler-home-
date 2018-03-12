package zeroEtc_designPattern.state_pattern.regular;

///**
// * 일반적인 상태 플래그값에따른 분기 처리1
// * state 전역변수 값(상태)에의해 doAction()내 분기문이 로직을 처리
// * 현상 : state 전역변수에 설정해야하는 값(상태)이 증가될때 doAction()내 
// *      분기문의 코드는 state 전역변수 설정값 종류 대비 몇배수 증가될수 있으며
// *      클래스의 코드라인 역시 증가되고, 코드의 분석 및 유지보수가 어려워짐.
// */
//public class TVRemoteBasic {
//
//	private String state = "";
//
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	public void doAction() {
//		if (state.equalsIgnoreCase("ON")) {
//			System.out.println("TV is turned ON");
//		} else if (state.equalsIgnoreCase("OFF")) {
//			System.out.println("TV is turned OFF");
//		}
//	}
//
//	public static void main(String[] args) {
//		TVRemoteBasic remote = new TVRemoteBasic();
//
//		remote.setState("ON");
//		remote.doAction();
//
//		remote.setState("OFF");
//		remote.doAction();
//	}
//}


///**
//* 일반적인 상태 플래그값에따른 분기 처리2(코드 리펙터링)
//*/
//public class TVRemoteBasic {
//
//	private String state = "";
//
//	public void setState(String state) {
//		this.state = state;
//		doAction();
//	}
//
//	public void doAction() {
//		if (state.equalsIgnoreCase("ON")) {
//			System.out.println("TV is turned ON");
//		} else if (state.equalsIgnoreCase("OFF")) {
//			System.out.println("TV is turned OFF");
//		}
//	}
//
//	public static void main(String[] args) {
//		TVRemoteBasic remote = new TVRemoteBasic();
//		remote.setState("ON");
//		remote.setState("OFF");
//	}
//}


/**
* 일반적인 상태 플래그값에따른 분기 처리3(코드 리펙터링)
*/
public class TVRemoteBasic {
	
	public static String doAction(String value){
		if (value.equalsIgnoreCase("ON")) {
			System.out.println("TV is turned ON");
		} else if (value.equalsIgnoreCase("OFF")) {
			System.out.println("TV is turned OFF");
		}
		return "";
	}

	public static void main(String[] args) {
		TVRemoteBasic.doAction("ON");
		TVRemoteBasic.doAction("OFF");
	}
}



