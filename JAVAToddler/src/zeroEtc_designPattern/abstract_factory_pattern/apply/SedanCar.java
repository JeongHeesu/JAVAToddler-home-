package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class SedanCar extends Car {
	
	public SedanCar(CarType model, Location location) {
		super(model, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("세단타입 차를 만듭니다. 기능은 마음대로 확장 할 수 있어요.");
	}

}
