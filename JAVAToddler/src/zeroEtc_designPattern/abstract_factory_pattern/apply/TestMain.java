package zeroEtc_designPattern.abstract_factory_pattern.apply;

public class TestMain {

	public static void main(String[] args) {
		try {
			HeadOfficeFactory.buildOder(CarType.SMALL, Location.SUNHWADONG);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
