package zero03_singolton_recursiveMethodCall;

// 클래스는 static 접근 제한자가 선언될 수 없음.
// 원래 static 접근 제한자가 선언되어 있으므로 중복 선언될 수 없다는 의미임.
public class OutSideClass {
	// inner class는 static 또는 non_static 일수 있음.
	class InnerOne{}
	
	// Nested Class : static으로 선언된 inner Class.
	// static 클래스는 static 클래스만 상속 가능.
	static class InnerTwo extends OutSideClass{
		int i = 0;
		static int j = 10;
		
	}
	
	// Nested Class : static으로 선언된 inner Class.
	// static 클래스가 아닌 일반 클래스를 상속할 수 없음
	static class InnerThree extends InnerTwo{}
	
	// inner class의 인스턴스 반환(O)
	public InnerOne getInstanceInnerOne1(){
		return new InnerOne();
	}
	
	// static 메서드에서 inner class의 인스턴스 반환(X)
	// static 메서드에서의 객체 생성은 nested class만 가능
//	public static InnerOne getInstanceInnerOne2(){
//		return new InnerOne();
//	}
	
	// static 메서드에서 nested class의 인스턴스 반환(O)
	public static InnerTwo getInstanceInnerTwo(){
		return new InnerTwo();
	}
	
	public static void main(String[] args){
		OutSideClass osc = new OutSideClass();
		
		// inner class의 생성
		OutSideClass.InnerOne io = osc.new InnerOne();
		
		// nested class의 생성
		InnerTwo iTwo = new OutSideClass.InnerTwo();
		InnerThree iThree = new OutSideClass.InnerThree();
	}
}
