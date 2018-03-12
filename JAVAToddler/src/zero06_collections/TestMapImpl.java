package zero06_collections;

public class TestMapImpl extends TestMap.SubClassMap implements TestMap, TestMap.SubTestMap {

	@Override
	public void testMapFunc() {
		System.out.println("interface TestMap 함수");
	}
	
	@Override
	public void SubMapFunc() {
		System.out.println("interface TestMap내 선언된 interface SubTestMap 함수");
	}

	@Override
	public void subClassFunc() {
		System.out.println("interface TestMap내 선언된 class SubClassMap 함수");
	}
	
	
}
