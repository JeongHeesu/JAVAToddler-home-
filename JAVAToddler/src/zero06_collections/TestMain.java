package zero06_collections;

public class TestMain {

	public static void main(String[] args) {
		// FailFastTest
//		FailFastTest ft = new FailFastTest();
//		ft.init();
		
		// enumeration만 동기화 보장 않함.
//		try {
//			ft.iteratorTest();
//			ft.enumerationTest();
//			ft.listTest();
//			ft.vectorTest();
//			ft.hashTableTest();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
//		TestWeakNSoftReference twnr = new TestWeakNSoftReference();
//		twnr.generalReference();
//		twnr.weakReference();
//		twnr.softReference();
		
		TestMapImpl tm = new TestMapImpl();
		tm.testMapFunc();
		tm.SubMapFunc();
		tm.subClassFunc();
	}

}
