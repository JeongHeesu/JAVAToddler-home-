package zeroEtc_designPattern.decorator_pattern.apply;

public class HoneyDecorator extends IceCreamDecorator {

	public HoneyDecorator(IceCream decoratedIceCream) {
		super(decoratedIceCream);
	}

	@Override
	public String makeIceCream() {
		return super.makeIceCream() + " sweet honey";
	}
}
