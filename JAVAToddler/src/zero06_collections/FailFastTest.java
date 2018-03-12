package zero06_collections;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class FailFastTest {
	private List<Integer> testList;
	private Vector<Integer> vecList;
	private Map<Integer, Integer> mapList;

	public void init(){
		System.out.println("================ 초기화 ================");
		testList = new ArrayList<Integer>();
		vecList = new Vector<Integer>();
		mapList = new Hashtable<Integer, Integer>();
		
		for(int i=0; i<1000; i++){
			testList.add(i);
			vecList.add(i);
			mapList.put(i, i);
		}	
	} 	

	public void iteratorTest() throws InterruptedException{
		System.out.println("======= iterator(fail-fast) ========");
		new AnotherThread().start();
		
		Iterator<Integer> it = testList.iterator();
		
		while(it.hasNext()){
			Thread.sleep(10);
			System.out.println(it.next());
		}
	}
	
	public void enumerationTest() throws InterruptedException{
		System.out.println("===== enumeration(fail-fast) ======");
		new AnotherThread().start();
		Enumeration<Integer> en = vecList.elements();
		
		while(en.hasMoreElements()){
			Thread.sleep(10);
			System.out.println(en.nextElement());
		}
	}
	
	public void listTest() throws InterruptedException{
		System.out.println("======= List(fail-fast) ========");
		new AnotherThread().start();
		
		for(Integer element : testList){
			Thread.sleep(10);
			System.out.println(element);
		}
	}
	
	public void vectorTest() throws InterruptedException{
		System.out.println("======= vector(fail-fast) ========");
		new AnotherThread().start();
		
		for(Integer element : vecList){
			Thread.sleep(10);
			System.out.println(element);
		}
	}
	
	public void hashTableTest() throws InterruptedException{
		System.out.println("====== hashtable(fail-fast) =======");
		new AnotherThread().start();
		
		Iterator<Entry<Integer, Integer>> it = mapList.entrySet().iterator();
		
		while(it.hasNext()){
			Thread.sleep(10);
			System.out.println(it.next());
		}
	}
	
	class AnotherThread extends Thread{
		@Override
		public void run(){
			System.out.println("start another thread!!");
			
			try{
				Thread.sleep(4000); // 4초 sleep
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			testList.remove(500); // iterator collection 객체 변경
			System.out.println("testList 500 요소값 삭제");
			vecList.remove(500);  // Vector collection 객체 변경
			System.out.println("vecList 500 요소값 삭제");
			mapList.remove(500); // HashTable collection 객체 변경
			System.out.println("mapList 500 요소값 삭제");
		}
	}
}