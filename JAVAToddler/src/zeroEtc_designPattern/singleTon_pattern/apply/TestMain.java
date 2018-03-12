package zeroEtc_designPattern.singleTon_pattern.apply;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestMain {

	public static void main(String[] args) {
		
		// Singleton pattern 테스트
		TargetClass tc1 = TargetClass.getInstance();
		
		try {
			Class<TargetClass> cls = 
					(Class<TargetClass>) Class.forName("zero23_designPattern.singleTon_pattern.TargetClass");
			Method method = cls.getDeclaredMethod("getInstance", null);
			TargetClass tc2 = (TargetClass) method.invoke(cls, null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
