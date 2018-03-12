package zeroEtc_designPattern.decorator_pattern.apply;

public class NuttyDecorator extends IceCreamDecorator {

	public NuttyDecorator(IceCream decoratedIceCream) {
		super(decoratedIceCream);
	}

	@Override
	public String makeIceCream() {
		return super.makeIceCream() + " cruncy nuts";
	}
}
