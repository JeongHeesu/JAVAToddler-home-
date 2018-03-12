package zero02_variable_constant_debug;


/**
 * <h4>자바 예약어(클래스명, 메서드 명, 변수명으로 활용할 수 없어요.)</h4>
 * <pre>
 * <a href="http://cfile25.uf.tistory.com/image/124A184F4DDCD8E221CEFB">더많은 정보를 원하십니까?</a>
 * </pre>
 * @author 전인호
 * @since 2013.04.15
 * @version 1.0.0
 */
public class KeywordNIdentifier {
	
	/**
	 * <h4>디버깅</h4>
	 * <pre>
	 *    1. 클래스 내 함수의 특정 위치에 breakpoint를 설정/삭제(ctrl+shift+b)
	 *    2. debug 모드(F11)로 어플리케이션을 실행
	 *    3. eclipse 퍼스텍티브는 debug 퍼스펙티브로 변경되고,
	 *       breakpoint가 설정된 해당 코드라인에서 프로세스 포커스가 정지하고 대기
	 *    4. F6 : 정지된 프로세스 포커스를 다음 라인으로 이동
	 *    5. F5 : 함수를 호출하는 코드라인에서 함수의 내부로 프로세스 포커스를 진입
	 *    6. F7 : 함수 내부로 진입된 이후 함수를 호출한 코드라인으로 프로세스 포커스 회귀
	 *    7. F8 : 현재의 프로세스 포커스에서 이후의 특정 브래이크포인트로 프로세스 포커스를 이동
	 *    8. Ctrl+F2 : 실행 종료
	 *    9. debug 퍼스펙티브내 Variables 뷰와 Breakpoint 뷰를 활용
	 * eclipse Debug Perspective의 Variables 뷰는 디버깅 작업 중 클래스, 변수, 배열 등의 생성 및
	 * 소멸과 값의 변경 여부를 모니터링 할수있습니다.
	 * <strong>
	 * <h4>발생된 에러의 해결을 위한 접근</h4>
	 * <strong>
	 *    1. 콘솔의 vertival scrollbar를 가장 아래로 위치시킴.
	 *    2. 콘솔화면의 가장 아래쪽부터 윗방향으로 팩키지.클래스명으로 열거된 코드라인을 읽으면서
	 *       자신이 프로그래밍한 팩키지.클래스명(라인번호)를 취득해 에러발생 코드라인을 파악함.
	 *    3. 익셉션 타입과 메세지를 분석함.
	 *    4. 익셉션 발생 코드라인 또는 상위에 브레이크 포인트를 설정하고 디버깅을 통해
	 *       해당 익셉션 발생 원인을 파악하고 수정함.
	 *       * 경우에따라 자신이 프로그래밍하지 않은, 활용된 라이브러리의 잘못된 사용으로 발생된 에러가
	 *         존재할수 있으며, 콘솔에 출력된 익셉션 타입과 메세지 분석으로 대응함.
	 *  </strong>
	 * </pre>
	 * @author 전인호
	 */
	public void keywordDeclaration(){
		System.out.println("자바는 문법적으로 미리 약속되어 사용되어지는 단어들이 있습니다. \n" + 
				"이것들을 예약어라고 합니다.\t그리고,\n" +
				"자바는 50개의 예약어를 가지며, 문법적으로 사용되어질 단어들이므로 사용자가 임의로\n" +
				"활용할수 없습니다.");
		
		this.methodIdentifier();
	}
	
	private void methodIdentifier(){
		String argument = "정말사용할수 없어요?";
		String returnValue =this.method_verification(argument);
		System.out.println(returnValue);
	}
	
	private String method_verification(String parameter){
		System.out.println(parameter);
		System.out.println("사실이에요. 예약어 그대로를 활용할 수 없어요.");
		
//		int int = 100;
		int _int = 100;
//		int if = 100;
		int _if = 100;
		
		float _abstract = 10.0f;
		
		return "사실이니까 " + (_int + _if) + "만번만 밎어주세요.";
	}
}
