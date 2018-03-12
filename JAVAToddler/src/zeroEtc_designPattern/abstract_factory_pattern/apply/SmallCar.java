package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class SmallCar extends Car {
	
	public SmallCar(CarType model, Location location) {
		super(model, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("소형차를 만듭니다. 기능은 마음대로 확장 할 수 있어요.");
	}

}
