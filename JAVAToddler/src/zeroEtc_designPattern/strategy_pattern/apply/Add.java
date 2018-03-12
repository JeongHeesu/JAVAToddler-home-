package zeroEtc_designPattern.strategy_pattern.apply;

public class Add implements Strategy {

	@Override
	public int execute(int i, int j) {
		System.out.println("Add execute");
		return i+j;
	}

}
