package zeroEtc_designPattern.pacade_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		// 사용자측은 단지 컴퓨터를 켜는것으로 백그라운드 단에서 컴퓨터가 제공하는 서비스를 
		// 활용함.
		new Computer().startComputer();
	}

}
