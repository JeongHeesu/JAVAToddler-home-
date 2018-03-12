package zero25_aop_code;

import org.apache.log4j.Logger;

public class ConsolePrtCls {
	
	public String value;
	
	public ConsolePrtCls() {
		System.out.println("ConsolePrtCls 생성자");
	}

	private void aspectjAdvantage1(){
		System.out.println("1. 다수의 클래스에서 활용되는 공통 코드(모듈)의 보일러판 코드베이스를 배제할수있음(DRY[Don't Repeat Yourself]).");
	}
	
	// 산재되어, 복잡한
	public String aspectjAdvantage2(String param1, String param2){
		return "2. " + param1 + " 존재하는 동일한 각 코드별로 존재하는 동일하고 " + param2 + " 의존관계를 해소할 수 있음.";
	}
	
	// 인터페이스, 추상클래스, 클래스 설계시
	protected String aspectjAdvantage3(String param){
		return "3. " + param + "의 단일역활원칙을 적용해 weaving될 공통모듈은 기타 클래스에 의존하지않는 코드시멘틱에 정합되도록 구현함.";
	}
	
	void aspectjAdvantage4(){
		System.out.println("4. 클래스 설계시 코드의 확장에는 개방되고, 수정에는 페쇄되어야하는 역활에 충실하게 됨.");
	}
	
	// Variable Parameter(가변인자형)
	// 변경되지않은, 약간의 변경된, 해소
	void aspectjAdvantage5(String ... params){
		System.out.println("5. " + params[0] + "상태 또는 " + params[1] + "상태로 다수 반복되는 코드의 중복을 " + params[2]);
	}
	
	public String arroundAdvantage(String param1, String param2){
		return "param1 : " + param1 + " / param2 : " + param2;
	}

	public void exceptionOccur(int i, int j){
		int rtn = i / j;
	}
	
}
