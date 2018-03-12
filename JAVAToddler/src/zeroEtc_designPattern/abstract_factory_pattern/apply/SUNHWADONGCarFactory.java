package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class SUNHWADONGCarFactory {
	
	public static Car buildCar(CarType model) throws Exception{
		Car car = null;
		switch (model) {
		case SMALL:
			car = new SmallCar(CarType.SMALL, Location.SUNHWADONG);
			break;
		case SEDAN:
			car = new SedanCar(CarType.SEDAN, Location.SUNHWADONG);
			break;
		case LUXURY:
			car = new LuxuryCar(CarType.SEDAN, Location.SUNHWADONG);
			break;
		default:
			throw new Exception("선화동에서 생산할수있는 차가 아닙니다.");
		}
		return car;
	}
}
