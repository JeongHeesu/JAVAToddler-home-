package zero02_variable_constant_debug;


/**
 * enum : 간단하게 활용될수있는 배열의 선언.(요일의 열거형, 계절의 열거형, 월의 열거형 등의 배열 형태.)
 * enum 타입 열거형 클래스에 열거된 값은 모두 static final이며, 열거값의 선언은 가장 최상위에 위치해야함. 
 * switch문의 인자로 활용될수 있으며 열거된 순서대로 ordinal(서수 first->second->third->fourth...) 
 * 기준 인덱스로 관리.
 * etc.)cardinal(기수 zero->one->two->three->four....)
 * 추가 정보의 타입으로 enum의 생성자 구성 후 활용.
 * @author Administrator
 */
public enum Season {
	
	SPRING(0,"쨈더워요"), SUMMER(1, "더워요"), AUTUMN(2, "쌀쌀해요"), WINTER(3, "무지추워요");

	private int index;
	private String ment;
	
	// enum 타입의 열거형 클래스의 생성자. 오직 private 접근지정자만을 정의할수 있으며,
	// 열거형의 생성시 해당 생성자를 활용하게되며(4개의 열거형 값이므로 생성자는 4번 활용됨.)  
	private Season(int index, String ment){
		System.out.println("enum 생성자");
		this.index = index;
		this.ment = ment;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public String getMent(){
		return this.ment;
	}
}