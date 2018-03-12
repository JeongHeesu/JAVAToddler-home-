package zero03_singolton_recursiveMethodCall.calc;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;


/**
 * <h4>연산자</h4>
 * <a href="http://cfile239.uf.daum.net/image/1525EA4C5021BEDE196B3B">연산자</a>
 * <h4>싱글톤 패턴을이용한 클래스의 인스턴스 취득</h4>
 * @author Administrator
 */
public class Calculation {

	/**
	 * <h4>static</h4>
	 * 일반적으로 클래스 내에 선언된 public 접근지정자가 선언된 변수는 해당 클래스의 인스턴스화 이후 접근 가능하다.
	 * <pre>
	 * static : 클래스 내에 선언된 static 정적 변수 또는 함수는 Class Loader를 통해 로딩 이후로 접근이
	 *         가능하며, 공유 또는 클래스 로딩에따른 초기화 로직 실행의 목적으로 활용한다.
	 *         상수 작성시 static final을 활용한다.
	 * </pre>
	 */
	private static Calculation calculation = null;
	private int cnt = 0;
	
	private Calculation(){}
	
	public  static Calculation getInstance(){
		if(calculation == null)
			calculation = new Calculation();
		return calculation;
	}
	
	// 문제 윤년 계산 : 4로 나누어 떨어지고, 100으로 나누어 떨어지지 않는 해 또는 400으로 나누어떨어지는 해
	public String leapYearCheck(int year){
		boolean yearTF = false;
		if((0 == (year%4) && 0 != (year%100)) || 0 == year%400)
			yearTF = true;
		else
			yearTF = false;

		if(yearTF){
			return year+"는 윤년입니다.";
		}else{
			return year+"는 윤년이 아닙니다.";
		}
	}
	
	public void envVariable(){
		// 해당 어플리케이션이 실행되는 시스템 속성 값 취득 및 출력
		System.out.println("========== 어플리케이션 실행 시스템 속성 취득 ===========");
		Properties properties = System.getProperties();
		// 컬렉션 순차접근 객체. 자바 초기버젼부터 지원되며, HashTable or Vector에서
		// 취득 가능.
		// Enumeration은 해당 컬렉션의 값을 복사해서 활용하므로 활용 중에
		// 해당 컬렉션의 값이 변경되어도 에러(ConcurrentModificationException) 발생(fail-fast)되지 않음.
		Enumeration<Object> enumers = properties.keys();
		while(enumers.hasMoreElements()){
			String key = (String)enumers.nextElement();
			System.out.println("properties key : " + key + " : " + 
					properties.getProperty(key));
		}
		
		System.out.println("========== 시스템 환경변수 취득(환경변수를 관리하지않는 OS도 있으므로 비추천) ===========");
		Map<String, String> map = System.getenv();
		// 컬렉션 순차접근 객체. 모든 컬렉션으로부터 취득 가능.
		// Iterator는 활용 중 해당 컬렉션의 추가, 삭제 작업으로인해
		// 컬렉션 값이 변경되면 에러(ConcurrentModificationException)발생(fail-fast)되며,
		// 모든 컬렉션이 이 경우에 해당되며, Enumeration만 제외됨. 
		Iterator<String> iter= map.keySet().iterator();
		while(iter.hasNext()){
			String key = (String)iter.next();
			System.out.println("env key : " + key + " : " + 
					map.get(key));
		}
	}
}






