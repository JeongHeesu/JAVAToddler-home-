package zeroEtc_designPattern.state_pattern.apply;

/**
 * state 전역변수의 값에따라 if문내에서 구성되어야할 확장될 소지가 있는 로직을 분리하여 분리된 코드를 포함하는 
 * 객체들을 작성하고, 구조화된 객체들을 활용해 state 전역변수의 상태값에따라 특정 클래스내에 집중될 코드들에서
 * 객체단위로 코드의 확장성을 제공하고 유지보수의 편리성을 도모함.
 */
public class TestMain {

	public static void main(String[] args) {
		TVContext context = new TVContext();

		context.setTvState(new TVStateOn());
		context.doAction();
		
		context.setTvState(new TVStateOff());
		context.doAction();
	}

}
