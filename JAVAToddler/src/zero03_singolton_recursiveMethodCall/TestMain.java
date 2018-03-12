package zero03_singolton_recursiveMethodCall;

import java.lang.reflect.Method;
import zero03_singolton_recursiveMethodCall.calc.Calculation;

	
public class TestMain {

	public static void main(String[] args) {
		Calculation calc1 = Calculation.getInstance();
		Calculation calc2 = Calculation.getInstance();
		Calculation calc3 = Calculation.getInstance();
		Calculation calc4 = null;
		
		try {
			// Calss : 실행중인 어플리케이션의 클래스 또는 인터페이스로서 ClassLoader에의해 취득됨.
			Class cls = Class.forName("zero03_singolton_recursiveMethodCall.calc.Calculation");
			
			// Method : 해당 클래스 또는 인터페이스의 단일 메서드 객체 또는 정보
			Method instanceMethod = cls.getDeclaredMethod("getInstance");
			// 파라메터의 열거 또는 파라메터 선언이 않된 메서드는 null, null 처리함.
			calc4 = (Calculation) instanceMethod.invoke(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("calc1 : " + calc1.hashCode() + 
				" / calc2 : " + calc2 +
				" / calc3 : " + calc3 + 
				" / calc4 : " + calc4);
		
		System.out.println("윤년여부 : " + calc1.leapYearCheck(2013));
		calc1.envVariable();
	}

}
