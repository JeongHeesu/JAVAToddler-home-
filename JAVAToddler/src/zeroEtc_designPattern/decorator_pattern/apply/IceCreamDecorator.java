package zeroEtc_designPattern.decorator_pattern.apply;

public abstract class IceCreamDecorator implements IceCream {
	protected IceCream decoratedIceCream;
	
	public IceCreamDecorator(IceCream decoratedIceCream) {
		this.decoratedIceCream = decoratedIceCream;
	}

	@Override
	public String makeIceCream() {
		return this.decoratedIceCream.makeIceCream();
	}

}
