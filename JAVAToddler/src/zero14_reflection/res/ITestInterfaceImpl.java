package zero14_reflection.res;

import zero16_ibatis.bean.MemberBean;

public class ITestInterfaceImpl implements ITestInterface {
	public String param1 = "1param";
	private String param2 = "2param";
	public static String param3 = "3param";
	
	@Override
	public String testMethod1() {
		System.out.println("testMethod1 호출");
		return "testMethod1";
	}

	@Override
	public void testMethod2(String value) {
		System.out.println("testMethod2 호출 : " + value);
	}

	@Override
	public MemberBean testMethod3() {
		System.out.println("testMethod3 호출 ");
		
		MemberBean member = new MemberBean();
		member.setMem_id("a001");
		member.setMem_pass("asdfasdf");
		return member;
	}

	@Override
	public MemberBean testMethod4(MemberBean value) {
		System.out.println("testMethod4 호출  : " + System.identityHashCode(value));
		
		MemberBean member = new MemberBean();
		member.setMem_id("a001");
		member.setMem_pass("asdfasdf");
		return member;
	}

	private String testMethod5(String value){
		System.out.println("testMethod5 호출 ");
		return "testMethod5";
	}
	
}
