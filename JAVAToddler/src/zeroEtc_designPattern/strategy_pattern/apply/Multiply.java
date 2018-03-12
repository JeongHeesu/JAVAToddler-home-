package zeroEtc_designPattern.strategy_pattern.apply;

public class Multiply implements Strategy {

	@Override
	public int execute(int i, int j) {
		System.out.println("Multiply execute");
		return i*j;
	}

}
