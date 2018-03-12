package zero02_variable_constant_debug;

/**
 * <h4>상수 만들기</h4>
 * <pre>
 * 상수(변해서는 않되는 값)를 만들기위해서 const 키워드를 사용하는 언어들이 있습니다.
 * 자바에서는 final 키워드를 이용해 변해서는 않될 상수를 선언합니다.
 * 언어별로 상수를 선언하는 키워드가 상이한데 C/C++의 경우 const 키워드를 사용하고
 * C#.Net은 readonly 키워드를 사용합니다.
 * </pre>
 * <h4>접근지정자</h4>
 * <pre>
 * 클래스는 private, protected, public, default 접근 지정자 중
 * public과 default 접근 지정자만 선언될 수 있어요.
 * <strong>private : </strong> 클래스 내 전역변수 또는 함수의 선언시 활용되며, 
 *                   접근 영역은 클래스 내부로 제한 및 상속 불가
 * <strong>protected : </strong> private 접근지정자의 성격을 가지지만 상속이 가능하며,
 *                   해당 클래스 또는 동일 팩키지/상이 팩키지에서 상속받은 클래스 그리고 동일 팩키지 내 클래스에서 접근 가능.
 * <strong>public : </strong> 클래스와 클래스 내 전역변수 또는 함수의 선언시 활용되며,
 *                   접근 영역은 해당 클래스와 동일한 팩키지 그외 팩키지에서 접근
 * <strong>default : </strong> 클래스와 클래스 내 전역변수 또는 함수의 선언시 접근 지정자 미지정시
 *                   자동으로 선언되며, 해당 클래스와 동일한 팩키지 내 클래스에서 접근 가능.
 * </pre>
 * @author Administrator
 */
public class ConstantCls {
	
	
	/**
	 * <h4>상수는 final 키워드를 선언합니다. </h4>
	 * <h4>상수는 값을 변경할 수 없으며, 반드시 선언 또는 생성자에서 초기화 합니다. </h4>
	 * <h4>함수에 final 키워드를 선언시 해당 함수는 자식 클래스내에서 오버라이딩될수 없습니다. </h4>
	 * <h4>클래스에 final 키워드를 선언시 해당 클래스는 상속될수 없습니다. </h4>
	 * <pre>
	 * 코드를 작성하기위해서는 많은 프로그래밍 규칙이 존재합니다.
	 * 그중 팩키지의 선언, 클래스의 선언, 변수, 상수의 선언에서 가장 기본적인 규칙을 준수하려
	 * 노력하는데 이를 표기법이라고 하며, 규칙 적용을 통해 코드의 가독성을 높이려하는 목적입니다.
	 * 표기법에는 카멜 표기법, 파스칼 표기법, 헝가리안 표기법 등이 존재합니다.
	 * 보통 팩키지명,메서드명, 변수명은 카멜(단어의 선두어는 소문자로 시작하고 단어가 바뀔때 선두어를 대문자로 함.)
	 * 표기법을 사용하고, 클래스명, 상수명은 파스칼(항상 단어의 선두어는 대문자로 시작하며, 상수명은 모두 대문자로
	 * 단어가 바뀔때 _로 구분) 표기법을 활용합니다.
	 * </pre>
	 */
	private final int MAX_VALUE = 100;
	public final int MIN_VALUE = 0;
	
	/**
	 * <h4>생성자 오버로딩</h4>
	 * <pre>
	 * 파라메터를 가지지 않은 기본 생성자는 선언하지 않아도 컴파일시 존재합니다.
	 * 기본 생성자는 내부에서 항상 자신의 부모의 생성자를 super();를 통해 호출합니다. 
	 * 생성자는 클래스 명과 동일한 메서드 선언 패턴으로 선언하지만 반환값을 가지지 않습니다.
	 * 파라메터의 타입, 순서, 갯수를 달리하며, 클래스명과 동일한 생성자를 선언할수있습니다.
	 * 이를 생성자 오버로딩이라고 합니다.
	 * 생성자 오버로딩시에는 기본 생성자를 선언해주어야 하며, 생성자 오버로딩은 해당 클래스의 인스턴스화시에
	 * 다양한 형태의 초기처리 또는 초기값을 가질수 있습니다.
	 * 생성자는 상속되지 않습니다.
	 * </pre>
	 */
	public ConstantCls() {
		// ConstantCls의 부모 클래스는 누구일까요?
		super();
		System.out.println("ConstantCls 생성자 - 파라메터 없음");
	}

	public ConstantCls(String parameter){
		System.out.println(parameter);
	}
	
	public ConstantCls(String parameter1, String parameter2){
		System.out.println(parameter1 + parameter2);
	}
	
	
	public void castingRange(){
		char cc='ㄱ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자
		cc='ㄴ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자
		cc='ㄷ';//
		System.out.println(cc);// char를 찍자
		System.out.println((int)cc); // int로 바꾸어 찍자

		System.out.println("---------------------------------");
		
		for(int i=1;i<=100;i++){
			System.out.print((char)i+" ");//찍고 공백 추가 그자리
			// (char)i --> int를 char로 casting
			if((i%10==0)){// 10으로 나누어 떨어지는 가?
				System.out.println();//한칸을 내려라
			}
		}
	}
	
	/**
	 * 자바에는 기본 데이터 타입이 존재합니다.
	 * <a href="http://thedailyjavas.com/wp-content/uploads/2012/10/Primitive-Data-Types.png">primitiveType</a>
	 * 다양한 타입 간 데이터의 전달을위해 Casting을 수행합니다.
	 * <strong>Narrowing Casting : </strong>특정 타입이 수용할수있는 최대값 이상의 큰값을 수용하도록 함. 
	 *                            손실 발생하며 명시적으로 캐스팅처리. 
	 *                            (디모션)
	 * <strong>Widening Casting : </strong>특정 타입이 수용할수있는 최대값 이하의 작은값을 수용하도록 함. 
	 *                            손실 발생하지 않음. 자동 캐스팅.
	 *                            (프로모션)
	 * 연산에따른 자동 형변환 : 
	 * <ul>
	 * <li>4byte int type 보다 작은 정수 데이타 타입의 연산 결과는 int로 자동 캐스팅 됨.</li>
	 * <li>큰 데이터 타입과 작은 데이터 타입의 연산 결과는 큰 데이터 타입으로 캐스팅 됨.</li>
	 * <li>정수 타입과 실수 타입의 연산결과는 실수타입으로 자동 캐스팅 됨.</li>
	 * </ul> 
	 * <h4>캐스팅 보드</h4>
	 * <pre>
	 * - : 자신
	 * N : 캐스팅 될수 없음(에러발생)
	 * C : Narrowing Casting으로 명시적으로 캐스팅 처리.
	 * Y : Widening Casting으로 자동 캐스팅 처리.
	 * Y*: Widening Casting으로 정수가 실수 타입으로 자동 캐스팅 처리.
	 *          boolean  byte short char int long float double
	 * boolean     -      N     N    N    N   N     N     N
	 * byte        N      -     Y    C    Y   Y     Y     Y
	 * short       N      C     -    C    Y   Y     Y     Y
	 * char        N      C     C    -    Y   Y     Y     Y
	 * int         N      C     C    C    -   Y     Y*    Y
	 * long        N      C     C    C    C   -     Y*    Y*
	 * float       N      C     C    C    C   C     -     Y
	 * double      N      C     C    C    C   C     C     -
	 * </pre>
	 * @param parameter
	 * @return int 문자열을 4byte int로 캐스팅 후 반환
	 * @exception java.lang.NumberFormatException
	 */
	public int castingMethod(String parameter){
		//Wrapper class (reference)
		Integer wrapInt    = new Integer(19);
		Double  wrapDouble = new Double(3.1453);
		
		char rtnChar = (char)65;
		int rtnInt = (int)'A';
		int rtnInt1 = (int)1.6f;
		float rtnFloat = (float)10;

		int i = 639;
	    String str1 = String.valueOf(i);
	    String str2 = Integer.toString(i);
	    
	    String val1 = "639";
	    // valueOf() : 10진 정수형 코드로 변환 후  처리가능.
	    // parseInt() : 10진 정수형 코드 외 2진코드, 8진코드, 16진코드로도 처리가능.
	    // valueOf()는 wrapping 객체를 반환하지만 해당 wrapping 객체를 기본 데이터 타입으로 unBoxing 후 
	    // 반환하기위해 아래의 형식을 활용.
	    // 기본 데이타 타입(primitive type)은 wraper 클래스가 존재함.
	    // 기본 데이타 타입 -> wraper class =  boxing
	    // wraper class -> 기본 데이타 타입  = unboxing 
	    int i1 = Integer.valueOf(val1).intValue();
	    int i2 = Integer.parseInt(val1);
	    long i3 = Long.parseLong(val1);
	    double j1 = Double.valueOf(val1).doubleValue();
	    
	    String val2 = "100";
	    String bin1 = Integer.toBinaryString(Integer.parseInt(val2));
	    String oct1 = Integer.toOctalString(Integer.parseInt(val2));
	    String hex1 = Integer.toHexString(Integer.parseInt(val2));
	    int hex = Integer.parseInt(val2, 16);
		    
		float rtnVal = Float.valueOf(Integer.toString(Integer.parseInt(parameter))+".5f").floatValue();
		return (int)rtnVal;
	}
	
}
