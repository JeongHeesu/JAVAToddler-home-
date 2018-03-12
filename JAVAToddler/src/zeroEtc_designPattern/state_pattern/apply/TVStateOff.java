package zeroEtc_designPattern.state_pattern.apply;

public class TVStateOff implements State {

	@Override
	public void doAction() {
		System.out.println("TV is turned OFF");
	}

}
