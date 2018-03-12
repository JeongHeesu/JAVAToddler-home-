package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class HeadOfficeFactory {
	// 산재된 클래스들을 그룹핑하여 활용처에 제공할수있는 자동화함.
	public static Car buildOder(CarType model, Location location) throws Exception{
		Car car = null;
		switch (location) {
		case ASIA:
			car = AsiaCarFactory.buildCar(model);
			break;
		case USA:
			car = USACarFactory.buildCar(model);
			break;
		case SUNHWADONG:
			car = SUNHWADONGCarFactory.buildCar(model);
			break;
		default:
			System.out.println("차 생산 오더를 내릴수있는 지역 공장이 없습니다.");
			break;
		}
		return car;
	}
}
