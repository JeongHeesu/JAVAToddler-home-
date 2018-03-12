package zero02_variable_constant_debug;

public class TestMain {
	
	/**
	 * 해당 TestMain.java의 오른쪽 마우스 클릭 후 Debug Configurations의 Arguments의
	 * Program Arguments에 몇몇의 값 입력 후 실행.
	 * @param args main 메서드에 전달되는 파라메터들
	 */
	public static void main(String[] args) {
		
		if(args != null){
			for(int i=0; i<args.length; i++){
				System.out.println("args : " + args[i]);
			}
		}
		// -----------------------------------------------------------------------
		
		KeywordNIdentifier keyword = new KeywordNIdentifier();
		keyword.keywordDeclaration();
		// -----------------------------------------------------------------------
		
		VariableTest vt = new VariableTest();
		vt.typeMethod();
		// -----------------------------------------------------------------------		
		
		try {
			// 클래스 로딩
			Class cls = Class.forName("zero02_variable_constant_debug.ConditionNRepetiveStmt");
			try {
				ConditionNRepetiveStmt cnr = (ConditionNRepetiveStmt) cls.newInstance();
				
				cnr.ifConditionMethod();
				cnr.switchConditionMethod();
				cnr.loopMethod();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// new 키워를 통해 인스턴스화된 객체는 JVM이 관리하는 Heap 영역내에 어딜지 모를 선두 메모리 주소를
		// 시작으로 자신의 영역을 만듭니다.
		ConstantCls cls1 = new ConstantCls();
		ConstantCls cls2 = new ConstantCls("ConstantCls 생성자 - 파라메터 한개");
		ConstantCls cls3 = new ConstantCls("ConstantCls 생성자 - 파라메터 ", "두개");
		// Heap 영역 내에서 해당 객체의 시작 주소를 정수로 표현하고, 객체를 구분할수있도록 hashCode()를 활용합니다.
		System.out.println("cls1 : " + cls1 + 
				" / hashCode : " + cls1.hashCode() + 
				" / 16진수 hashCode" + Integer.toHexString(cls1.hashCode()));

		System.out.println("cls2 : " + cls2 + 
				" / hashCode : " + cls2.hashCode() + 
				" / 16진수 hashCode" + Integer.toHexString(cls2.hashCode()));
		
		System.out.println("cls3 : " + cls3 + 
				" / hashCode : " + cls3.hashCode() + 
				" / 16진수 hashCode" + Integer.toHexString(cls3.hashCode()));	
		// -----------------------------------------------------------------------
		
		cls1.castingRange();
		// -----------------------------------------------------------------------
		
		/**
		 * 에러의 분류
		 * 1. Syntex Error(Compile Error) : 문법적인 오류로인해 컴파일시 발생됨.
		 * 2. Runtime Error : 컴파일은 정상적으로 수행되었지만 해당 어플리케이션 실행시
		 *                    논리적인 로직 구성 오류로인해 발생
		 *                    debugging 작업을 통해 해당 오류의 수정이 요구됨.
		 * 
		 * try{}catch문은 에러가 예상되는 코드를 랩핑하기위해 선언합니다.
		 * catch블럭은 다수 선언될수 있으며 작은범위의 에러 취득에서 점차 넓은 범위의 에러취득으로
		 * 확장되어야합니다.
		 * 또, try{} 내 중첩된 try{}catch{}문이 선언될수있습니다. 
		 * 
		 */
		try{
			// try 블럭 내 선언된 변수의 영역은 해당 블럭 내에 한정됩니다.
			int rtnVal = cls1.castingMethod("삼");
		}catch (NumberFormatException e) {	
			System.out.println("NumberFormatException : 해당 메서드 내에서 전달한 " +
					"아귀먼트 '삼'을 정수로 변경할때 발생.");
			// catch 블럭 내 선언된 변수의 영역은 해당 블럭 내에 한정됩니다.
			// rtnVal = cls1.castingMethod("3");
			int rtnVal = cls1.castingMethod("3");
			System.out.println("rtnVal : " + rtnVal);
		}catch(IllegalArgumentException e){
			System.out.println("IllegalArgumentException : 올바르지 않은 아귀먼트를 메서드에 전달해 해당 " +
					"메서드내에서 에러가 발생했을때 발생");
		}catch(RuntimeException e){
			System.out.println("RuntimeException : JVM이 해당 어플리케이션 실행 단계에서 발생하는 모든 종류의 " +
					"에러 중 실행 단계에서의 최상위의 에러");
		}catch(Exception e){
			System.out.println("Exception : 자바 언어 내 모든 언어의 최상위에 존재하며, 상속으로 에러클래스가 구현됨.");
		}finally{
			// finally 블럭 내 선언된 변수의 영역은 해당 블럭 내에 한정됩니다.
			
		}
		// -----------------------------------------------------------------------
		
		try {
			// 클래스 로더를 이용해 해당 클래스의 static 전역변수, 함수가 정적영역내에 생성
			// 클래스 로더 : .class를 jvm(javaw.exe)에 배치 및 링크.
			//           Load-time dynamic loading - 클래스 로딩시 해당 클래스 내에 선언된 모든 클래스(전역변수, 메서드 파라메터 등) 로딩
			//           Run-time dynamic loading  - 코드내에서 객체 참조 선언시 클래스가 로딩.
			//                                       Class.forName("")
			//                                                     클래스 로더
			//              ExtClassLoader            extends      AppClassLoader       extends      SubAppClassLoader        extends ServletContainerClassLoader
			// Standalone : JAVA_HOME/jre/lib/rt.jar               클래스 패스의 *.class                 import jar내 *.class                 해당 없음.  
			//              JAVA_HOME/jre/lib/ext/*.jar               
			// Web        :      동일                                               동일                                                동일                                               HttpServlet, Framework, JDBC Driver			
			Class cls = Class.forName("zero02_variable_constant_debug.BlockTest");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 정적영역내에 static 블럭은 생성되어있으므로, 일반 블럭만 생성.
		BlockTest bt = new BlockTest();
		
		// try{}catch{} 문 내에서의 return 처리.
		int argument = 1;
//		int argument = 0;
		System.out.println(bt.returnNtryBlock(argument));
		
		bt.repeatedTryBlock(0);
	}
}







