package zeroEtc_designPattern.factory_method_pattern.apply;

// 팩토리 클래스 내 객체 생성 코드가 집중되어 관리되고, 확장될수있으며,
// 사용처는 IHoSil 인터페이스를 통해 다양한 구현클래스의 인터페이스를 제공받아
// 활용할수있는 유연성을 제공함.
public class HoSilFactoryCls {
	public static IHoSil getHosilInstance(String hosilValue){
		if("201".intern() == hosilValue.intern()){
			return new HoSil201("교수", "김정환", "042-222-8202", "자바");
		}else if("202".intern() == hosilValue.intern()){
			return new HoSil202();
		}else if("203".intern() == hosilValue.intern()){
			return new HoSil203();
		}else if("204".intern() == hosilValue.intern()){
			return new HoSil204();
		}else{
			return null;
		}
	}
}
