package zeroEtc_designPattern.strategy_pattern.apply;

public class Subtract implements Strategy {

	@Override
	public int execute(int i, int j) {
		System.out.println("Subtract execute");
		return i-j;
	}

}
