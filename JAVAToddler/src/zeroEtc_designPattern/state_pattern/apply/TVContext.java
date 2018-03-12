package zeroEtc_designPattern.state_pattern.apply;

// 상태에따라 분리된 클래스들의 선택 실행 클래스
public class TVContext implements State {
	private State tvState;
	
	public State getTvState() {
		return tvState;
	}

	public void setTvState(State tvState) {
		this.tvState = tvState;
	}

	@Override
	public void doAction() {
		// TVStateOn 객체가 setTvState()를 통해 제공되어지면 TVStateOn의 doAction() 함수가 호출됨.
		// TVStateOff 객체가 setTvState()를 통해 제공되어지면 TVStateOff의 doAction() 함수가 호출됨.		
		this.tvState.doAction();
	}

}
