package zeroEtc_designPattern.decorator_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		IceCream iceCream = new HoneyDecorator(new NuttyDecorator(new BaseIceCream()));
		System.out.println(iceCream.makeIceCream());
	}

}
