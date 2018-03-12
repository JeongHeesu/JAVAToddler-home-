package zero07_threadIntermidate;

public class TestMain {

	public static void main(String[] args) {
		// 케익 접시 준비
		CakePlate cake=new CakePlate();
		
		// 케익 먹기
		CakeEater eater=new CakeEater(cake);
		
		// 케익 만들기
		CakeMaker baker=new CakeMaker(cake);

		// 케익 만들기 시작		
		baker.start();
		
		// 먹기 시작
		eater.start();
	}
}
