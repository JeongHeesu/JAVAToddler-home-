package zeroEtc_designPattern.strategy_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		Context context = new Context(new Add());
		System.out.println(context.executeStrategy(10, 10));
	}

}
