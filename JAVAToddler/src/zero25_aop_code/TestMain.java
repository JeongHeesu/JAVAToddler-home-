package zero25_aop_code;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import zero16_ibatis.bean.MemberBean;

public class TestMain {
	
	public TestMain(String param) {
		System.out.println(param);
	}

	public static void main(String[] args) {
		TestMain tm = new TestMain("TestMain 생성자");
		
		ConsolePrtCls cp = new ConsolePrtCls();
		
		// ################### execution ###################		
		try {
			cp.value = "AspectJ의 장점은..";
			String val = cp.value;
			
			// ConsolePrtCls의 private void aspectjAdvantage1() 동적 호출 
			Method aspectjAdvantage1PrivateMethod = 
					cp.getClass().getDeclaredMethod("aspectjAdvantage1", null);
			aspectjAdvantage1PrivateMethod.setAccessible(true);
			aspectjAdvantage1PrivateMethod.invoke(cp, null);
			
			System.out.println(cp.aspectjAdvantage2("산재되어", "복잡한"));
			System.out.println(cp.aspectjAdvantage3("인터페이스, 추상클래스, 클래스 설계시"));
			cp.aspectjAdvantage4();
			cp.aspectjAdvantage5("변경되지않은", "약간의 변경된", "해소");
			
			// arround advice 테스트
			System.out.println(cp.arroundAdvantage("전달값1", "전달값2"));

			// 익셉션 발생
			try{
				cp.exceptionOccur(1, 0);
			}catch (Exception e) {}
			
			// ################### within ###################
			IMemberService service = new IMemberServiceImpl();
			service.getMemberInfo(new HashMap<String, String>());
			service.getMemberList();
			service.insertMemberInfo(new MemberBean());
			
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
