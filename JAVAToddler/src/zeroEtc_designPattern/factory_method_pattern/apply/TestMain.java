package zeroEtc_designPattern.factory_method_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		// 활용측 클래스(TestMain 클래스에서는 HoSilFactoryCls 및 HoSilFactoryCls 뒷단
		// 클래스들에대한 정보가 전혀 없는 관계 즉, 느슨한연결[loosely coupled]인 상태 관계를 갖음.)
		// 느슨한 연결 : 서로 다른 객체 간 정보를 알수없는 상태의 관계
		IHoSil hosil = HoSilFactoryCls.getHosilInstance("202");
		System.out.print("홍길동님이 ");
		hosil.entrance();
		
		hosil = HoSilFactoryCls.getHosilInstance("204");
		System.out.print("박길동님이 ");
		hosil.entrance();
	}

}
