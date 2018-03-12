package zero02_variable_constant_debug;

import java.util.Random;

import sun.beans.editors.IntEditor;

/**
 * <h4>조건문과 반복문</h4>
 * <strong>조건문</strong>
 * <pre>
 * if문, 3항연산자, switch문으로 구성되며 해당 조건의 참, 거짓 또는 일치값에의해
 * 로직이 분기됨.
 * </pre>
 * <strong>반복문</strong>
 * <pre>
 * for문, while문, do~while문으로 구성되며 해당 조건의 초기값 또는 참, 거짓의해 
 * 특정 로직을 반복 처리함.
 * </pre>
 * @author Administrator
 *
 */
public class ConditionNRepetiveStmt {
	private static final int MAX_VALUE = 100;
	public static final int MIN_VALUE = 0;

	// \x00 의 16진법 표현(hex코드)
	// \u0000의 16진법 표현(유니코드)
	private char cc;
	
	public int[] ifConditionMethod(){
		// 전역변수는 해당 타입의 default 값으로 초기화됩니다.
		// 지역 변수는 반드시 초기화되어야 합니다.
		int i;
//		 int j = i; // 에러 발생
		char c;
		
		if(true){
			System.out.println("조건이 참일때 블럭 내 진입");
		}
		if(!false){
			System.out.println("조건은 참이 아니지만 조건부정으로 블럭내 진입");
		}
		// 기본 데이타 타입은 상수가 아닌이상 동적(stack)영역에 저장되며, 기본데이타 타입의 wrapper 클래스는
		// 힙 영역에 저장(heap상 해당 클래스 대상 메모리 할당되고 해당 메모리의 첫주소를 가르키는 레퍼런스 변수가 존재)됨.
		// boxing : 기본 데이타 타입 -> 해당 기본 데이타 타입을 wrapper 클래스로 변경
		// unboxing : 해당 기본 데이타 타입을 wrapper 클래스 -> 동적(stack)영역의 기본 데이타 타입 변경
		// Integer i = 10;          auto boxing 됨
		// int i = new Integer(10); auto unBoxing 됨
		
		// 동적(stack)영역(레퍼런싱 전문의 선 스캐닝 후 처리됨.)       리터럴(특정 변수에 할당되는 값 자체) 영역(저장소)
		// primitiveInt1(0x01) ========================>  100(0x001) System.identityHashcode()
		// primitiveInt2(0x01)                            로 리터럴 영역 내 주소비교

		int primitiveInt1 = 100;
		
		// 앞서 선언된 primitiveInt1의 동적영역 내 시작 주소와 이후 선언되는 
		// primitiveInt2의 동적영역내 시작 주소를 리터럴 영역의 선 스캐닝 작업 
		// 수행으로 동일한 값일때 primitiveInt1의 동적 영역의 시작 주소와 
		// primitiveInt2 동일하게 가르키도록 하므로 동적영역내 주소가 동일함.
		// 동일한 주소를 참조하는것을 앨리어싱이라고 함.
		int primitiveInt2 = 100;
		
		System.out.println("primitiveInt1의 리터럴 영역 내 주소 : " + 
				System.identityHashCode(primitiveInt1));
		System.out.println("primitiveInt2의 리터럴 영역 내 주소 : " + 
				System.identityHashCode(primitiveInt2));
		
		// == 는 리터럴 영역의 주소를 System.identityHashCode()를 통해 비교함.
		if(primitiveInt1 == primitiveInt2){
			System.out.println("같다");
		}
		
		// 힙 영역에 생성됨.
		Integer intWrapping1 = new Integer(100);
		Integer intWrapping2 = new Integer(100);
		
		// 힙 영역에 생성된 랩퍼 객체는 각각 다른 주소에 해당 값을 저장함.
		// new 키워드를 이용한 힙영역(객체화되어 메모리에 값을 가지고 저장) 
		// intWrapping1(0x01) 100객체.hashCode()로 값 비교
		// intWrapping1(0x02) 
		System.out.println("intWrapping1의 힙 영역 내 주소 : " + 
				System.identityHashCode(intWrapping1));
		System.out.println("intWrapping2의 힙 영역 내 주소 : " + 
				System.identityHashCode(intWrapping2));
		
		// hashCode()는 동일한 값을 반환함.
		System.out.println("intWrapping1의 힙 영역 내 주소 : " + 
				intWrapping1.hashCode());
		System.out.println("intWrapping1의 힙 영역 내 주소 : " + 
				intWrapping2.hashCode());
		
		// 객체는 값이 같아도 서로 다른 메모리 영역을 차지함.
		// ==는 System.identityHashCode()를 활용해 동적 영역의 주소를 
		// 비교 조건으로하며 주소가 다르므로 출력되지 않음.
		if(intWrapping1 == intWrapping2){
			System.out.println("같다");
		}
		// equals()는 해당 객체의 hashcode값을 비교함.
		if(intWrapping1.equals(intWrapping2)){
			System.out.println("같다");
		}
		
		// intWrapping1이 primitiveInt1로 auto unBoxing되고 동일한 
		// 리터럴 영역내 주소를 선스캐닝 후 앨리어싱 하므로 같음.
		if(primitiveInt1 == intWrapping1){
			System.out.println("같다");
		}
		
		Random random = new Random();
		boolean boolVal = random.nextBoolean();
		if(boolVal){
			System.out.println("랜덤 불린타입 값 true");
		}else{
			System.out.println("랜덤 불린타입 값 false");
		}
		
		// 3항 연산자
		String rtn = (boolVal) ? "랜덤 불린타입 값 true" : "랜덤 불린타입 값 flase";
		System.out.println(rtn);
		
		
		// nextInt(5)는 0~4까지의 난수가 발생되므로 1~5범위를 위해 + 1
		int numVal = random.nextInt(5)+1;
		if(numVal == 1){
			System.out.println("랜덤 정수타입 값 : " + numVal);
		}else if(numVal == 2){
			System.out.println("랜덤 정수타입 값 : " + numVal);
		}else if(numVal == 3){
			System.out.println("랜덤 정수타입 값 : " + numVal);
		}else if(numVal > 3 && numVal < 5){
			System.out.println("랜덤 정수타입 값 : " + numVal);
		}else{
			System.out.println("랜덤 정수타입 값 : 5");
		}
		
		// 3항 연산자
		String rtnVal1 = (numVal == 2) ? "랜덤 정수타입 값 " + numVal : "";
		String rtnVal2 = (numVal != 2) ? "랜덤 정수타입 값 " + numVal : "";
		String rtnVal3 = (numVal > 2) ? "랜덤 정수타입 값 " + numVal : "";
		String rtnVal4 = (numVal >= 2) ? "랜덤 정수타입 값 " + numVal : "";
		
		// Math.random()은 double type의 0.0에서 1.0 사이 난수를 발생
		// 26개의 알파벳 생성을 위해 0~25범위를 지정 후 알파벳 대문자는 65부터 시작하므로
		// 가산함.
//		String alpha = String.valueOf(((Math.random()*26) + 65)).toString();
		String alpha = String.valueOf((char)65).toString();
		if("A" ==  alpha){
			System.out.println("랜덤 문자열 값 == 비교 " + alpha);
		}
		if("A".equals(alpha)){
			System.out.println("랜덤 문자열 값 equals 비교 " + alpha);
		}
		
		return new int[]{ numVal };
	}
	
	public String[] switchConditionMethod(){
		System.out.println("switch문");
		
		Season thisSeason = Season.SPRING;
		for(Season season : Season.values()){
			System.out.println("index : " + season.getIndex() +
					" / ment : " + season.getMent() +
					" / ordinal : " + season.ordinal() + 
					" / name : " + season.name());
			enumView(season);
		}
		
		String val1 = "012345";
		char[] val2 = val1.toCharArray();
		String val3 = String.valueOf(val2);
		
		// switch문의 인자는 int로 캐스팅이 가능한 모든 타입(byte, short, int, char)이 활용됨.
		// break; 가 없을때의 반응으로 0123찍힘..
		switch(Integer.parseInt(val3.substring(0,1))){
		case 0:
			System.out.print("0");
		case 1:
			System.out.print("1");
		case 2:
			System.out.print("2");
		case 3:
			System.out.print("3");
			break;
		}
		System.out.println();
		return new String[10];
	}
	
	private void enumView(Season season){
		// int 타입의 인자 외에 enum 상수도 인자로 활용됨.
		// case Season.Spring: 할수 없음.
		switch(season){
		case SPRING:
			System.out.println("봄");
			break;
		case SUMMER:
			System.out.println("여름");
			break;
		case AUTUMN:
			System.out.println("가을");
			break;
		case WINTER:
			System.out.println("겨울");
			break;
		default:
			System.out.println("봄");
		}
	}
	
	
	/**
	 * <h4>메서드의 접근 지정자</h4>
	 * <pre>메서드는 private, protected, public, default 접근 지정자가 선언될수 있어요.</pre>
	 */
	void loopMethod(){
		// for 반복문은 최초 반복 로직을위해 선언된 변수의 값을 초기화 합니다.
		// 선언된 변수는 for 반복문 블럭내에서 유효한 지역변수이며, for문 외부에서는 접근할 수 없어요.
		// 초기화 후 바로 조건을 검사하고 조건이 true일때 for 반복문 블럭으로 진입합니다.
		for(int i=MIN_VALUE; i>MAX_VALUE; i++){
			System.out.println("지역 변수 i를 초기화 하지만, 조건을 true로 만족하지 못하므로 이 문자열은 출력되지 않아요.");
		}
		
		for(int i=MIN_VALUE; i<MAX_VALUE; i++){
			System.out.println("for 반복문 블록 내 반복을위해 변수 i의 값을 증가(" + i + ")하고 " +
					"조건 검사를 반복하며 조건이 false일때 종료됩니다. ");
		}
		
		System.out.println("등비수열 : 일정한 비율로 곱해서 증가하는 수의 열");
		for(int i=1; i<20; i*=2){
			System.out.print(i + "  ");
		}
		
		System.out.println("등차수열 : 일정한 비율만큼 감소하는 수의 열");
		for( int i=250; i>0;i-=50){
			System.out.print(i + "  ");
		}
		
		int i = 0;
		for(; i<10;i++){
			if(i%2==0){
				continue;
			}
			System.out.print(" [ "+i+" ] ");
		}
		
		for(;;){
			System.out.println("변수의 초기화 및 조건등이 생략되면 무한루프에 빠지게 됩니다.");
			if(i++ == 11){
				// 무한루프란 특정 처리가 무한대로 반복되고 해당 처리에서 벗어나지 못하는 상태로
				// 반복문(for, while, do-while 등)에서 벗어나기위해 break;를 활용합니다.
				break;
			}
		}
		
		for(int k=0,m=1; k<4||m<2; k++,m++){ 
			System.out.println(k+"  <===k  m===> "+m);
		}
		
		for(int j=0; j<10; j++){
			if(j%2 == 0) continue;
			System.out.print(" [ "+i+" ] ");
		}
		
		
		System.out.println();
		// 한글자음 찍기. 유니코드에는 한글이 11172자로 정의되어있음
		// 유니코드 내에서의 한글첫글자의 시작은 0xac00(44032).
		int cnt = 1;
		boolean escape = false;
		while(!escape){
			char val = (char)((Math.random()*11172) + 0xAC00);
			System.out.print(val);
			if(cnt%10 == 0)
				System.out.println();
			while(cnt == 2000){
				// 중첩된 반복문에서 break;는 해당 반복문 탈출
				// 바깓쪽 반복문 탈출을위한 escape = true 설정
				escape = true;
				break;
			}
			cnt++;
		}
		
		do{
			// do~while문은 do블럭 내 최초 1회 진입 후 while 조건을
			// 검증 후 true일때까지 반복.
			System.out.println("정답");
		}while("강남멋쟁이".equals("지종호"));
	}
}
