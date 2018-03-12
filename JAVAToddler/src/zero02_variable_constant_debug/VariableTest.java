package zero02_variable_constant_debug;

public class VariableTest {
	
	/**
	 * <h4>기본 데이터 타입의 선언과 활용.</h4>
	 * <strong>java 32bit runtime과 64bit runtime </strong>
	 * <ul>
	 * <li>java의 32bit runtime 보다 64bit runtime 환경은 Heap Memory size가 70% 증가</li>
	 * <li>단순 수치연산(int, float, double)은 당연히 64bit쪽이 처리속도가 빠름</li>
	 * <li>클래스의 객체 할당과 관련된 로직 처리는 64bit쪽이 32bit에 비해 증가된 메모리 영역의 영향으로 느림</li>
	 * <li>java 32bit 컴파일 결과는 64bit runtime에서 실행가능</li>
	 * <li>java 64bit 컴파일 결과는 32bit runtime에서 실행가능</li>
	 * <li>자바 버젼 확인 cmd => java -version</li>
	 * </ul>  
	 * <a href="http://3.bp.blogspot.com/-Dvvjxh03gqQ/TbYyIQlJheI/AAAAAAAAACU/Ak5fiID4O6k/s1600/Capture.PNG">기본 데이터 타입의 범위(32bit) 기준</a>
	 */
	public void typeMethod() {
		// 프로모션 : 연산 또는 묵시적/명시적 형변환을통해 해당 자료형이 더 큰 자료형으로 변환되는 것으로 
		//         값 손실 없음.
		// 디모션 : 명시적 형변환을 통해 해당 자료형이 작은 자료형으로 변환되는 것으로 값 손실 발생. 
		
		// 정수형 1byte
		// msb(부호 양수=0, 음수=1)
		// 최대값 : 01111111
		// 최소값 : 10000000
		byte ba = 10;
		byte bb = 20;
		byte bc = 10 + 20;          // (int+int) 자동 캐스팅
		byte bd = (byte) (ba + bb); // (byte)(int+int)
		
		// -1 할당됨.
		byte be = (byte) Integer.MAX_VALUE;
		System.out.println("01 : " + bc);
		System.out.println("02 : " + bd);
		System.out.println("03 : " + be);

		// 유일한 부호있는(양수) 정수형 2byte
		// 최대값 : 1(msb로 사용하지 않음)111111111111111
		char a = 'a';
		char b = 'b';
		char c = 'c';
        char d = 97;		
		System.out.println("a : " + a);
		System.out.println("b 정수 : " + (int)b);
		System.out.println("c 정수 : " + (int)c);
		System.out.println("d : " + d);
		System.out.println("최소값 : " + (int)Character.MIN_VALUE);
		System.out.println("최대값 : " + (int)Character.MAX_VALUE);
		

		// 정수형 2byte
		// 최소값 : 1000000000000000
		// 최대값 : 0111111111111111
		short sa = 30;
		short sb = 30;
		System.out.println("sa : hashcode - " + System.identityHashCode(sa));
		System.out.println("sb : hashcode - " + System.identityHashCode(sb));
		System.out.println("sa와 sb에 할당된 값이 저장되는 동적메모리 영역 내 리터럴 영역에는 30이라는 값이 " +
				"저장되고, 리터럴 영역에 값 저장시 JVM은 해당 값이 기존에 존재하는지 스캔하고 없으면 신규 생성하고 " +
				"존재하면 해당 값을 그대로 활용함.");
		
		short sc = 30 + 30;           // (int+int) 자동캐스팅
		short sd = (short) (sa + sb); // (short)(int+int)
		// -1 할당됨.
		short se = (short) Integer.MAX_VALUE;
		System.out.println("04 : " + sc);
		System.out.println("05 : " + sd);
		System.out.println("06 : " + se);

		// 정수형 4byte
		int ia = 20;
		int ib = 30;
		int ic = 20 + 30;
		int id = ic + sa;
		System.out.println("07 : " + ic);

		int ie = Integer.MAX_VALUE;
		System.out.println("08_1 : " + ie);
		ie++;
		// -2147483648 출력됨.(최대값을 넘기면 최소값부터 다시 연산됨.)
		System.out.println("08_2 : " + ie);

		int _if = 10/3;
		System.out.println("08_3 : " + _if);
		
		// 정수형 8byte
		long la = 40l;
		long lb = 50L;
		long lba = 50 + Integer.MAX_VALUE;  // (int+int)
		long lbb = 50L + Integer.MAX_VALUE; // (long+int)
		// (int+int) 연산에서 int의 최대값 이상의 값으로 오버플로우 됨.
		// 오버플로우 : 해당 타입이 최대값의 범위를 벋어난 값의 할당시 발생.
		//          오버플로우된 값은 최대값->최소값으로 반복됨.
		long lbc = 1000000 * 1000000;       
		// (int+long) 자동 캐스팅
		long lbd = 1000000 * 1000000L;
		long lc = la + lb; // (long+long)
		System.out.println("09 : " + lba);
		System.out.println("10 : " + lbb);
		System.out.println("11 : " + lc);
		
		// 실수형 4byte
		float fa = 45.0f;
		float fb = 46.67F;
		float fd = 30; 
		float fe = fa + fb;

		// 실수형 4byte
		double da = 12;
		double db = 45 + Float.MAX_VALUE;
		double dc = da + db;
		System.out.println("12 : " + dc);
		
		// (int/int)
		double dd = 10/3;
		System.out.println("13_1 : " + dd);
		// (int/double)
		double de = 10/3.0;
		System.out.println("13_2 : " + de);
		
		System.out.println("14 : " + "byte 범위: " + Byte.MIN_VALUE + " ~ "
				+ Byte.MAX_VALUE);
		System.out.println("15 : " + "short 범위: " + Short.MIN_VALUE + " ~ "
				+ Short.MAX_VALUE);
		System.out.println("16 : " + "int 범위: " + Integer.MIN_VALUE + " ~ "
				+ Integer.MAX_VALUE);
		System.out.println("17 : " + "long 범위: " + Long.MIN_VALUE + " ~ "
				+ Long.MAX_VALUE);
		System.out.println("18 : " + "float 범위: " + Float.MIN_VALUE
				+ " ~ " + Float.MAX_VALUE);
		System.out.println("19 : " + "double 범위: " + Double.MIN_VALUE
				+ " ~ " + Double.MAX_VALUE);
		
		String sta = "가나다";
		String stb = "가나다";
		System.out.println("sta : hashcode - " + sta.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(sta));
		System.out.println("stb : hashcode - " + stb.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(stb));
		
		// identityHashCode값이 동일하므로 같음.(문자열 리터럴일 경우에 한함.)
		if(sta == stb){
			System.out.println("같다");
		}else{
			System.out.println("틀리다");
		}

		String stc = new String("가나다");
		String std = new String("가나다");
		System.out.println("stc : hashcode - " + stc.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(stc));
		System.out.println("std : hashcode - " + std.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(std));
		
		// 문자열 객체를 identityHashCode()를 통해 == 를 비교하려하므로 틀림.
		// equals()를 활용해 저장된 값 자체를 비교하도록 해야함.
		if(stc == std){
			System.out.println("같다");
		}else{
			System.out.println("틀리다");
		}

		String ste = "가나다";
		String stf = new String("가나다");
		System.out.println("ste : hashcode - " + ste.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(ste));
		System.out.println("stf : hashcode - " + stf.hashCode() 
				+ " / identityHashCode - "+System.identityHashCode(stf));
		
		// 문자열 객체를 identityHashCode()를 통해 == 를 비교하려하므로 틀림.
		// equals()를 활용해 저장된 값 자체를 비교하도록 해야함.
		if(ste == stf){
			System.out.println("같다");
		}else{
			System.out.println("틀리다");
		}
		
		this.func1();
	}
	
	// 전역변수는 해당 클래스내 어떤 메서드에서도 접근, 활용, 수정될수 있으며,
	// 변경된 값은 공유됨.
	private int cnt = 10;
	
	private void func1(){
		System.out.println("cnt : " + (cnt++));
		this.func2();
	}
	private void func2(){
		System.out.println("cnt : " + (cnt++));
		this.func3();
	}
	private void func3(){
		System.out.println("cnt : " + (cnt++));
		this.func4();
	}
	private void func4(){
		System.out.println("cnt : " + (cnt++));
	}
}
