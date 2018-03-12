package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class LuxuryCar extends Car {
	
	public LuxuryCar(CarType model, Location location) {
		super(model, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("럭셔리한 차를 만듭니다. 기능은 마음대로 확장 할 수 있어요.");
	}

}
