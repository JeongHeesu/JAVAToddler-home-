package zeroEtc_designPattern.state_pattern.apply;

public class TVStateOn implements State {

	@Override
	public void doAction() {
		System.out.println("TV is turned ON");
	}

}
