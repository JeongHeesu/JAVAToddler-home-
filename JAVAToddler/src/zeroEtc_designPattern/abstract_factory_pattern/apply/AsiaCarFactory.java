package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class AsiaCarFactory {
	
	public static Car buildCar(CarType model) throws Exception{
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar(CarType.SMALL, Location.ASIA);
			break;
		case SEDAN:
			car = new SedanCar(CarType.SEDAN, Location.ASIA);
			break;
		case LUXURY:
			car = new LuxuryCar(CarType.SEDAN, Location.ASIA);
			break;
		default:
			throw new Exception("아시아지역에서 생산할수있는 차가 아닙니다.");
		}
		return car;
	}
}
