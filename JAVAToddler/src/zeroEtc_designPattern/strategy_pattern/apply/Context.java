package zeroEtc_designPattern.strategy_pattern.apply;

public class Context {
	private Strategy strategy;
	
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public int executeStrategy(int i, int j){
		return this.strategy.execute(i, j);
	}
	
}
