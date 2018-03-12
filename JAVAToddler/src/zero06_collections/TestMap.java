package zero06_collections;

public interface TestMap {
	public void testMapFunc();
	
	interface SubTestMap{
		public void SubMapFunc();
	}
	
	class SubClassMap{
		public void subClassFunc(){
			System.out.println("subClassFunc()");
		}
	}
	
}
