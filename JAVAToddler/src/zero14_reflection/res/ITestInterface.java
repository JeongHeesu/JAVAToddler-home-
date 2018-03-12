package zero14_reflection.res;

import zero16_ibatis.bean.MemberBean;

public interface ITestInterface {
	public String testMethod1();
	public void testMethod2(String value);
	public MemberBean testMethod3();
	public MemberBean testMethod4(MemberBean value);
}
