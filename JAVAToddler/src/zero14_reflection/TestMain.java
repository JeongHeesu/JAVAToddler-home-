package zero14_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import zero04_String_date.common.CommonUtil;

public class TestMain {

	public static void main(String[] args) {
		// java.lang.Class
		Class it;
		try {
			it = Class.forName("zero14_reflection.res.ITestInterfaceImpl");

			System.out.println("class canonicalName : " + it.getCanonicalName());
			System.out.println("class Name : " + it.getName());
			
			// 생성자 취득
			Constructor itConstructor = it.getConstructor();
			// 인스턴스화
			Object itimpl = itConstructor.newInstance();
			
			// public 메서드 정보
			Method[] methods = it.getMethods();
			for(Method method : methods){
				System.out.print("method name : " + method.getName() + 
						" / return type : " + method.getReturnType().getName());
				System.out.print("\t");
				Class[] cls = method.getParameterTypes();
				for(Class cl : cls){
					System.out.println("param name : " + cl.getName());
				}
			}
			
			// 특정 public 메서드 호출. testMethod1()   
			//                     -> it.getMethod("testMethod1", null)
			//                     testMethod1(MemberBean member)
			//                     -> it.getMethod("testMethod1", MemberBean.Class)
			//                     testMethod1(String val)
			//                     -> it.getMethod("testMethod1", String.Class)
			//                     testMethod1(MemberBean member, String val)
			//                     -> it.getMethod("testMethod1", MemberBean.Class, String.Class)
			// invoke시에 파라메터를 맞춰서 해당 메서드 호출.
			Method testMethod1 = it.getMethod("testMethod1", null);
			System.out.println("testMethod1 호출 : " + testMethod1.invoke(itimpl, null));
			
			// private 메서드 호출
			Method testMethod5 = it.getDeclaredMethod("testMethod5", String.class);
			testMethod5.setAccessible(true);
			System.out.println("testMethod5 호출 : " + testMethod5.invoke(itimpl, "전달값"));
			
			System.out.println();
			
			Field[] fields = it.getFields();
			
			// public 변수 접근
			for(Field field : fields){
				String accessModifier = "";
				switch (field.getModifiers()) {
				case Modifier.PRIVATE:
					accessModifier = "private";
					break;
				case Modifier.PUBLIC:
					accessModifier = "public";
					break;
				case Modifier.PROTECTED:
					accessModifier = "protected";
					break;
				default:
					accessModifier = "default";
					break;
				}
				System.out.println("접근지정자 : " + accessModifier +
						" / 자료타입 : " + field.getType().getName() +
						" / 전역 변수명 : " + field.getName() + 
						" / 값 : " + field.get(itimpl));  
			}
			
			// public static 변수값 접근
			try {
				System.out.println("static 변수 값 : " + it.getField("param3").get(null));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			
			
			// private 변수 접근
			try {
				Field privateField = it.getDeclaredField("param2");
				privateField.setAccessible(true);
				System.out.println("private 변수 값 : " + privateField.get(itimpl));
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
















